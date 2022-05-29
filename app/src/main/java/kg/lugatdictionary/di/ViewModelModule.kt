package kg.lugatdictionary.di

import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.vm.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainVM() }
    viewModel { SearchVM(get(), get()) }
    viewModel { LaunchVM(get(), get()) }
    viewModel { WidgetVM(get(), get()) }
    viewModel { FavoriteVM(get(), get()) }
    viewModel { HistoryVM(get(), get()) }
    viewModel { parameters -> WordDetailVM(word = parameters.get(), get(), get(), get(), get(), get(), get()) }
}