package com.squareup.dcto19.cashapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
  private lateinit var component: MainComponent

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    component = DaggerMainComponent.builder().build()
    setContentView(R.layout.activity_main)
  }

  internal fun mainComponent() = component
}
