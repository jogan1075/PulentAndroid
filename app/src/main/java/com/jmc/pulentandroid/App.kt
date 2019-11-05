package com.jmc.pulentandroid

import android.app.Application
import com.jmc.pulentandroid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * Created by Jmunoz
 */

open class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                appModule
            )
        }
    }
}