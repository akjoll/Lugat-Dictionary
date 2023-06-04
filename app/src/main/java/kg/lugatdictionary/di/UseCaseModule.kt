package kg.lugatdictionary.di

import kg.lugatdictionary.domain.usecases.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetWordsBySearchUC(get()) }
    factory { GetWordsUC(get()) }
    factory { SaveWordsUC(get()) }
    factory { CheckFavoriteUC(get()) }
    factory { CheckWidgetUC(get()) }
    factory { DeleteFavoriteUC(get()) }
    factory { DeleteWidgetUC(get()) }
    factory { DeleteHistoryUC(get()) }
    factory { InsertFavoriteUC(get()) }
    factory { InsertWidgetUC(get()) }
    factory { InsertHistoryUC(get()) }
    factory { GetFavoritesUC(get()) }
    factory { GetWidgetsUC(get()) }
    factory { GetHistoriesUC(get()) }
}