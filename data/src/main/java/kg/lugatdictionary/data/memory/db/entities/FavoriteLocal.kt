package kg.lugatdictionary.data.memory.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kg.lugatdictionary.data.utils.FavoriteLocalTableName
import kg.lugatdictionary.domain.entities.Word

@Entity(tableName = FavoriteLocalTableName)
data class FavoriteLocal(
    @PrimaryKey
    val id: Long,
    val word: String,
    val explanation: String
)

fun FavoriteLocal.toWord() = Word(
    id = id,
    word = word,
    explanation = explanation
)
