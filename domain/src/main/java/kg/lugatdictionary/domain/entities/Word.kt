package kg.lugatdictionary.domain.entities

data class Word (
    val id: Long,
    val word:String,
    val explanation: String,
    val date: String? = null
)
