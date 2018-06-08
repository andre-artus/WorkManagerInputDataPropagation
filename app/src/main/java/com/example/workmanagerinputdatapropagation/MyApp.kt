package com.example.workmanagerinputdatapropagation

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

/**
 * Created by andre
on 2018/06/08.
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            AndroidThreeTen.init(this)
        }
    }
}