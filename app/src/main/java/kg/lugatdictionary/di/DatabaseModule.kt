package kg.lugatdictionary.di


import kg.lugatdictionary.data.memory.db.provideAppDatabase
import kg.lugatdictionary.data.memory.db.provideLugatDictDao
import org.koin.dsl.module

val databaseModule = module {
    single { provideAppDatabase(get()) }
    single { provideLugatDictDao(get()) }
}