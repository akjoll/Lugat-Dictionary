package kg.lugatdictionary.di

import kg.lugatdictionary.data.network.service.MainService
import kg.lugatdictionary.data.utils.provideNetworkService
import org.koin.dsl.module

val apiServiceModule = module {

    factory { provideNetworkService(MainService::class.java, get()) }
}