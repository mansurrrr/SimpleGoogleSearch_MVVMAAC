package com.ms.simplegooglemapsearch_test.base

import android.app.Application
import android.util.Log

class BaseApplication: Application() {
    val TAG = BaseApplication::class.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Application Started")
    }
}