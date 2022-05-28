package kg.lugatdictionary.di

import kg.lugatdictionary.vm.LaunchVM
import kg.lugatdictionary.vm.MainVM
import kg.lugatdictionary.vm.SearchVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainVM() }
    viewModel { SearchVM(get()) }
    viewModel { LaunchVM(get(), get()) }
}