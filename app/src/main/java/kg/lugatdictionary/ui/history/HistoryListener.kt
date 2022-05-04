package kg.lugatdictionary.ui.history

interface HistoryListener {
    fun onWordClicked(position: Int, title: String)
}