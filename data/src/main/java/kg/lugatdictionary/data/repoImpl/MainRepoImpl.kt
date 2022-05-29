package kg.lugatdictionary.data.repoImpl


import kg.lugatdictionary.data.memory.db.daos.LugatDictDao
import kg.lugatdictionary.data.memory.db.entities.*
import kg.lugatdictionary.data.memory.preferences.LugatSharedPreferences
import kg.lugatdictionary.data.network.service.MainService
import kg.lugatdictionary.data.utils.service
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.repository.MainRepo
import kg.lugatdictionary.domain.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MainRepoImpl(
    private val service: MainService,
    private val localDataSource: LugatDictDao,
    private val sharedPref: LugatSharedPreferences
) : MainRepo {
    override suspend fun fetchWords() = flow {
        emit(service { service.getWords() }.map { words -> words.map { it.toWord() } })
    }

    override suspend fun saveWords(words: List<Word>) = flow {
        localDataSource.saveWords(words.map { it.toWordLocal() }).apply {
            emit(Either.Right(BaseUseCase.None))
        }
    }

    override suspend fun getWords() =
        localDataSource.getWords().map { wordLocal ->
            Either.Right(wordLocal.map { it.toWord() })
        }

    override suspend fun getFavorites() =
        localDataSource.getFavorites().map { favoriteLocal ->
            Either.Right(favoriteLocal.map { it.toWord() })
        }

    override suspend fun getWidgets() =
        localDataSource.getWidgets().map { widgetLocal ->
            Either.Right(widgetLocal.map { it.toWord() })
        }

    override suspend fun getHistories() =
        localDataSource.getHistories().map { historiesLocal ->
            Either.Right(historiesLocal.map { it.toWord() })
        }

    override suspend fun getWordsBySearch(searchText: String) =
        localDataSource.getWordsBySearch(searchText).map { wordLocal ->
            Either.Right(wordLocal.map { it.toWord() })
        }

    override suspend fun checkFavorite(id: Long) =
        localDataSource.checkFavorite(id).map {
            Either.Right(it)
        }

    override suspend fun checkWidget(id: Long) =
        localDataSource.checkWidget(id).map {
            Either.Right(it)
        }

    override suspend fun deleteFavorite(word: Word) = flow {
        localDataSource.deleteFavorite(word.toFavoriteLocal()).apply {
            emit(Either.Right(false))
        }
    }


    override suspend fun deleteWidget(word: Word) = flow {
        localDataSource.deleteWidget(word.toWidgetLocal()).apply {
            emit(Either.Right(false))
        }
    }

    override suspend fun deleteHistory(word: Word) = flow {
        localDataSource.deleteHistory(word.toHistoryLocal()).apply {
            emit(Either.Right(false))
        }
    }

    override suspend fun insertWidget(word: Word) = flow {
        localDataSource.insertWidget(word.toWidgetLocal()).apply {
            emit(Either.Right(true))
        }
    }

    override suspend fun insertFavorite(word: Word) = flow {
        localDataSource.insertFavorite(word.toFavoriteLocal()).apply {
            emit(Either.Right(true))
        }
    }

    override suspend fun insertHistory(word: Word) = flow {
        localDataSource.insertHistory(word.toHistoryLocal()).apply {
            emit(Either.Right(BaseUseCase.None))
        }
    }
}

