package kg.lugatdictionary.data.memory.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kg.lugatdictionary.data.utils.FavoriteLocalTableName
import kg.lugatdictionary.data.utils.WidgetLocalTableName
import kg.lugatdictionary.domain.entities.Word

@Entity(tableName = WidgetLocalTableName)
data class WidgetLocal(
    @PrimaryKey
    val id: Long,
    val word: String,
    val explanation: String
)

fun WidgetLocal.toWord() = Word(
    id = id,
    word = word,
    explanation = explanation
)