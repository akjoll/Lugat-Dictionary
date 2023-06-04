package kg.lugatdictionary.data.memory.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kg.lugatdictionary.data.utils.WordLocalTableName
import kg.lugatdictionary.domain.entities.Word

@Entity(tableName = WordLocalTableName)
data class WordLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val word: String,
    val explanation: String
) {
    fun toWord() = Word(
        id = id,
        word = word,
        explanation = explanation
    )

}

fun Word.toWordLocal() = WordLocal(
    id = id,
    word = word,
    explanation = explanation
)

