package kg.lugatdictionary.data.memory.db.daos

import androidx.room.*
import androidx.room.OnConflictStrategy.*
import kg.lugatdictionary.data.memory.db.entities.FavoriteLocal
import kg.lugatdictionary.data.memory.db.entities.HistoryLocal
import kg.lugatdictionary.data.memory.db.entities.WidgetLocal
import kg.lugatdictionary.data.memory.db.entities.WordLocal
import kg.lugatdictionary.data.utils.FavoriteLocalTableName
import kg.lugatdictionary.data.utils.HistoryLocalTableName
import kg.lugatdictionary.data.utils.WidgetLocalTableName
import kg.lugatdictionary.data.utils.WordLocalTableName
import kotlinx.coroutines.flow.Flow

@Dao
interface LugatDictDao {

    @Insert(entity = WordLocal::class, onConflict = IGNORE)
    suspend fun saveWords(words: List<WordLocal>)

    @Query("SELECT * from $WordLocalTableName")
    fun getWords(): Flow<List<WordLocal>>

    @Query("SELECT * from $WordLocalTableName WHERE word LIKE :searchText || '%' ORDER BY word")
    fun getWordsBySearch(searchText: String): Flow<List<WordLocal>>

    @Query("SELECT * from $FavoriteLocalTableName")
    fun getFavorites(): Flow<List<FavoriteLocal>>

    @Query("SELECT * from $WidgetLocalTableName")
    fun getWidgets(): Flow<List<WidgetLocal>>

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteLocal)

    @Delete
    suspend fun deleteWidget(widgetLocal: WidgetLocal)

    @Delete
    suspend fun deleteHistory(historyLocal: HistoryLocal)

    @Insert(entity = FavoriteLocal::class, onConflict = IGNORE)
    suspend fun insertFavorite(favorite: FavoriteLocal)

    @Insert(entity = WidgetLocal::class, onConflict = IGNORE)
    suspend fun insertWidget(widgetLocal: WidgetLocal)

    @Insert(entity = HistoryLocal::class, onConflict = REPLACE)
    suspend fun insertHistory(historyLocal: HistoryLocal)

    @Query("SELECT * from $HistoryLocalTableName")
    fun getHistories(): Flow<List<HistoryLocal>>

    @Query("SELECT EXISTS (SELECT * from $FavoriteLocalTableName WHERE id = :id)")
    fun checkFavorite(id: Long): Flow<Boolean>

    @Query("SELECT EXISTS (SELECT * from $WidgetLocalTableName WHERE id = :id)")
    fun checkWidget(id: Long): Flow<Boolean>
}