package com.squareup.dcto19.cashapp

import com.squareup.dcto19.cashapp.backend.GreeterBackendModule
import dagger.Component

@Component(modules = [GreeterBackendModule::class])
interface MainComponent {
  fun inject(view: GreeterView)
}
