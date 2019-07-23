package com.example.movies.app

import android.app.Application
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho
import okhttp3.OkHttpClient

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

    }

}