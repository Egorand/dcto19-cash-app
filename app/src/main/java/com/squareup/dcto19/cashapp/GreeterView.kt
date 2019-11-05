package com.squareup.dcto19.cashapp

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.dcto19.cashapp.presenters.GreeterPresenter
import com.squareup.dcto19.cashapp.presenters.GreetingViewEvent
import com.squareup.dcto19.cashapp.presenters.GreetingViewEvent.GetGreeted
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class GreeterView(
  context: Context,
  attrs: AttributeSet?
) : ConstraintLayout(context, attrs) {
  private lateinit var name: TextView
  private lateinit var greet: View
  private lateinit var message: TextView
  private lateinit var spinner: View

  @Inject internal lateinit var presenter: GreeterPresenter

  private val viewEvents = BehaviorSubject.create<GreetingViewEvent>()
  private lateinit var disposables: CompositeDisposable

  init {
    inflate(context, R.layout.view_greeter, this)
    val component = (context as MainActivity).mainComponent()
    component.inject(this)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    name = findViewById(R.id.name)
    greet = findViewById(R.id.greet)
    message = findViewById(R.id.message)
    spinner = findViewById(R.id.spinner)

    greet.setOnClickListener {
      name.visibility = GONE
      greet.visibility = GONE
      viewEvents.onNext(GetGreeted(name.text.toString()))
    }
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    disposables = CompositeDisposable()
    if (isInEditMode) return

    disposables.add(viewEvents
        .compose(presenter)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { (greeting, loading) ->
          message.text = greeting?.message
          spinner.visibility = if (loading) VISIBLE else GONE
        })
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    disposables.dispose()
  }
}
