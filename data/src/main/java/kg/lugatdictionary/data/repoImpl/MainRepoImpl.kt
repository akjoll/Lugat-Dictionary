package kg.lugatdictionary.data.repoImpl


import kg.lugatdictionary.data.memory.db.daos.LugatDictDao
import kg.lugatdictionary.data.memory.db.entities.WordLocal
import kg.lugatdictionary.data.memory.db.entities.toWordLocal
import kg.lugatdictionary.data.memory.preferences.LugatSharedPreferences
import kg.lugatdictionary.data.network.service.MainService
import kg.lugatdictionary.data.utils.service
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.repository.MainRepo
import kg.lugatdictionary.domain.utils.*
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

    override suspend fun getWords() = flow {
        emit(Either.Right(emptyList<Word>()))
    }

    override suspend fun getWordsBySearch(searchText: String) =
        localDataSource.getWordsBySearch(searchText).map {
            Either.Right(it.map { it.toWord() })
        }
}

