package kg.lugatdictionary.ui.utils.extensions

inline fun <reified T> tryCast(instance: Any?, block: (T) -> Unit) {
    if (instance is T) block(instance)
}