package org.khairy.brova

import android.app.Application
import org.khairy.brova.di.appModule
import org.khairy.brova.features.login.di.loginModule
import org.khairy.brova.features.login.di.registerModule
import org.khairy.brova.features.login.di.scanQRModule
import org.khairy.brova.features.savesizes.di.takeMeasuresModule
import org.khairy.brova.features.scanhistory.di.scanHistoryModule
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
                loginModule,
                registerModule,
                scanQRModule,
                takeMeasuresModule,
                scanHistoryModule
            )
        }
    }
}