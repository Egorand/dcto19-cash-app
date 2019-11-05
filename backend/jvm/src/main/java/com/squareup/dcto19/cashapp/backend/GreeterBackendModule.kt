package com.squareup.dcto19.cashapp.backend

import dagger.Binds
import dagger.Module

@Module
abstract class GreeterBackendModule {
  @Binds internal abstract fun bindRealGreeter(greeter: RealGreeter): Greeter
}
