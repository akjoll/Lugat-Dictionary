package kg.lugatdictionary.domain.repository

import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.domain.utils.Either
import kg.lugatdictionary.domain.utils.Failure
import kotlinx.coroutines.flow.Flow


interface MainRepo {

    suspend fun fetchWords(): Flow<Either<Failure, List<Word>>>

    suspend fun saveWords(words: List<Word>): Flow<Either<Failure, BaseUseCase.None>>

    suspend fun getWords(): Flow<Either<Failure, List<Word>>>

    suspend fun getFavorites(): Flow<Either<Failure, List<Word>>>

    suspend fun getWidgets(): Flow<Either<Failure, List<Word>>>

    suspend fun getHistories(): Flow<Either<Failure, List<Word>>>

    suspend fun getWordsBySearch(searchText: String): Flow<Either<Failure, List<Word>>>

    suspend fun checkFavorite(id: Long): Flow<Either<Failure, Boolean>>

    suspend fun checkWidget(id: Long): Flow<Either<Failure, Boolean>>

    suspend fun deleteFavorite(word: Word): Flow<Either<Failure, Boolean>>

    suspend fun deleteWidget(word: Word): Flow<Either<Failure, Boolean>>

    suspend fun deleteHistory(word: Word): Flow<Either<Failure, Boolean>>

    suspend fun insertWidget(word: Word): Flow<Either<Failure, Boolean>>

    suspend fun insertFavorite(word: Word): Flow<Either<Failure, Boolean>>

    suspend fun insertHistory(word: Word): Flow<Either<Failure, BaseUseCase.None>>

}