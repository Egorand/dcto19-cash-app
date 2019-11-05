package com.squareup.dcto19.cashapp.backend

import io.reactivex.Observable

interface Greeter {
  fun greeting(name: String): Observable<Greeting>

  data class Greeting(val message: String)
}
