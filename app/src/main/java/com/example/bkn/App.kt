package com.example.bkn

import android.app.Application
import com.example.bkn.di.AppComponent
import com.example.bkn.di.DaggerAppComponent
import com.example.bkn.di.modules.DatabaseModule
import com.example.bkn.di.modules.DomainModule
import com.example.bkn.di.modules.RemoteModule

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}