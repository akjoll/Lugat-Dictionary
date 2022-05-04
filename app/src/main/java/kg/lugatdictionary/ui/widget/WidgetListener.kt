package kg.lugatdictionary.ui.widget

interface WidgetListener {
    abstract fun onWordClicked(position: Int, title: String)
}