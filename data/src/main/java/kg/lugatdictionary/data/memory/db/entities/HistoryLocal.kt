package kg.lugatdictionary.data.memory.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kg.lugatdictionary.data.utils.FavoriteLocalTableName
import kg.lugatdictionary.data.utils.HistoryLocalTableName
import kg.lugatdictionary.domain.entities.Word
import java.sql.Date

@Entity(tableName = HistoryLocalTableName)
data class HistoryLocal(
    @PrimaryKey
    val id: Long,
    val word: String,
    val explanation: String,
    val date: String
)

fun HistoryLocal.toWord() = Word(
    id = id,
    word = word,
    explanation = explanation,
    date = date
)