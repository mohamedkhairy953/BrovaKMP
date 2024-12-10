package org.khairy.brova

import android.app.Application
import org.khairy.brova.di.appModule
import org.khairy.brova.features.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

/**
 * Application class
 */
class ProvaApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ProvaApp)
            modules(
                appModule,
                loginModule
            )
        }
    }
}