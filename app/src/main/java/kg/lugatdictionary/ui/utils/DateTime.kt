package kg.lugatdictionary.ui.utils

import java.text.SimpleDateFormat
import java.util.*

const val ISO_FORMAT = "HH:mm   dd.MM.yyyy"

fun getDate(format: String = ISO_FORMAT): String{
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(Date())
}