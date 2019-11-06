package com.squareup.dcto19.cashapp;

import android.app.Application;
import android.util.Log;

public class DCTO19CashApp extends Application {
  @Override public void onCreate() {
    super.onCreate();
    Log.d("DCTO19CashApp", "App created!");
  }
}
