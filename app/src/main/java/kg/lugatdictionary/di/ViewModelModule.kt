package kg.lugatdictionary.di

import kg.lugatdictionary.vm.MainVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainVM() }
}