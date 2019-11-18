package com.droid.cleanarchitecture

import android.app.Application
import android.content.Context
import com.droid.cleanarchitecture.di.repoModule
import com.droid.cleanarchitecture.di.usecaseModule
import com.droid.cleanarchitecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class CAApplication : Application() {

    companion object {
        lateinit var application: CAApplication

        fun getApplication(): Application? {
            return application
        }

        fun getContext(): Context {
            return getApplication()!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        application = this

        startKoin {
            androidLogger()
            androidContext(this@CAApplication)
            loadKoinModules(listOf(viewModelModule, usecaseModule, repoModule))
        }

    }
}