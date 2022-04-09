package kg.lugatdictionary

import android.app.Application
import kg.lugatdictionary.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(
                apiServiceModule,
                useCaseModule,
                repositoryModule,
                viewModelModule,
                utilsModule
            ))
        }

    }
}