package kg.lugatdictionary.di

import kg.lugatdictionary.data.repoImpl.MainRepoImpl
import kg.lugatdictionary.domain.repository.MainRepo
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepo> { MainRepoImpl(get(), get(), get()) }
}