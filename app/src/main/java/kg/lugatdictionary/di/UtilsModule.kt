package kg.lugatdictionary.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import kg.lugatdictionary.data.memory.LugatSharedPreferences
import kg.lugatdictionary.data.memory.provideLugatSharedPref

val utilsModule = module {
    single {
        LugatSharedPreferences(
            provideLugatSharedPref(
                androidApplication()
            )
        )
    }
}