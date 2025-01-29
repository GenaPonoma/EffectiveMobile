package com.example.effectivemobile.app

import android.app.Application
import com.example.effectivemobile.di.AppComponent
import com.example.effectivemobile.di.AppModule
import com.example.effectivemobile.di.DaggerAppComponent


class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }
}