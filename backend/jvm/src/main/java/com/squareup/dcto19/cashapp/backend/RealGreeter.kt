package com.squareup.dcto19.cashapp.backend

import com.squareup.dcto19.cashapp.backend.Greeter.Greeting
import io.reactivex.Observable
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Inject

internal class RealGreeter @Inject constructor() : Greeter {
  override fun greeting(name: String): Observable<Greeting> {
    return Observable.just(Greeting(message = "Hello $name!"))
        .delay(2, SECONDS)
  }
}
