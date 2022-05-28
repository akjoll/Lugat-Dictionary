package kg.lugatdictionary.data.network.entities

import com.google.gson.annotations.SerializedName
import kg.lugatdictionary.domain.entities.Word

data class WordResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String
){
    fun toWord() = Word(
        id = id,
        word = name,
        explanation = description
    )
}

