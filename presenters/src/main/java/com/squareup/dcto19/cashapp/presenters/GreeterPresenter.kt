package com.squareup.dcto19.cashapp.presenters

import com.squareup.dcto19.cashapp.backend.Greeter
import com.squareup.dcto19.cashapp.backend.Greeter.Greeting
import com.squareup.dcto19.cashapp.presenters.GreetingViewEvent.GetGreeted
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class GreeterPresenter @Inject constructor(
  private val greeter: Greeter
) : ObservableTransformer<GreetingViewEvent, GreetingViewModel> {
  override fun apply(upstream: Observable<GreetingViewEvent>): ObservableSource<GreetingViewModel> {
    println("yeah")
    return upstream
        .filter { event -> event is GetGreeted }
        .cast(GetGreeted::class.java)
        .switchMap { event ->
          Observable.concat(
              Observable.just(GreetingViewModel(greeting = null, loading = true)),
              greeter.greeting(event.name)
                  .map { greeting -> GreetingViewModel(greeting, loading = false) }
          )
        }
  }
}

sealed class GreetingViewEvent {
  data class GetGreeted(val name: String) : GreetingViewEvent()
}

data class GreetingViewModel(
  val greeting: Greeting?,
  val loading: Boolean
)
