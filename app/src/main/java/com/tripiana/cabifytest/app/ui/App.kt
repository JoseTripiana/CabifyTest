package com.tripiana.cabifytest.app.ui

import android.app.Application
import com.tripiana.cabifytest.app.di.apiModule
import com.tripiana.cabifytest.app.di.appModule
import com.tripiana.cabifytest.app.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            androidFileProperties()
            modules(appModule, repositoryModule, apiModule)
        }
    }
}