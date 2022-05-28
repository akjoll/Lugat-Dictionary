package kg.lugatdictionary.data.memory.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kg.lugatdictionary.data.memory.db.entities.FavoriteLocal
import kg.lugatdictionary.data.memory.db.entities.HistoryLocal
import kg.lugatdictionary.data.memory.db.entities.WidgetLocal
import kg.lugatdictionary.data.memory.db.entities.WordLocal
import kg.lugatdictionary.data.utils.FavoriteLocalTableName
import kg.lugatdictionary.data.utils.HistoryLocalTableName
import kg.lugatdictionary.data.utils.WidgetLocalTableName
import kg.lugatdictionary.data.utils.WordLocalTableName
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.domain.utils.Either
import kg.lugatdictionary.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

@Dao
interface LugatDictDao {

    @Insert(entity = WordLocal::class, onConflict = IGNORE)
    suspend fun saveWords(words: List<WordLocal>)

    @Query("SELECT * from $WordLocalTableName")
    fun getWords(): Flow<List<WordLocal>>

    @Query("SELECT * from $WordLocalTableName WHERE word LIKE '%' || :searchText || '%' OR explanation LIKE '%' || :searchText || '%'")
    fun getWordsBySearch(searchText: String): Flow<List<WordLocal>>

    @Query("SELECT * from $FavoriteLocalTableName")
    fun getFavorites(): Flow<List<FavoriteLocal>>

    @Query("SELECT * from $WidgetLocalTableName")
    fun getWidgets(): Flow<List<WidgetLocal>>

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteLocal)

    @Query("SELECT * from $HistoryLocalTableName")
    fun getHistories(): Flow<List<HistoryLocal>>
}