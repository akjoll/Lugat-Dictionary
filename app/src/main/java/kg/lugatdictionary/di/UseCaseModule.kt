package kg.lugatdictionary.di

import kg.lugatdictionary.domain.usecases.GetWordsBySearchUC
import kg.lugatdictionary.domain.usecases.GetWordsUC
import kg.lugatdictionary.domain.usecases.SaveWordsUC
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetWordsBySearchUC(get()) }
    factory { GetWordsUC(get()) }
    factory { SaveWordsUC(get()) }
}