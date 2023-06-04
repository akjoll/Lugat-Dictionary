package kg.lugatdictionary.ui.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import kg.lugatdictionary.databinding.ItemsWidgetBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.favourite.WordListeners
import kg.lugatdictionary.ui.utils.base.BaseAdapter

class WidgetAdapter(private val listener: WordListeners):BaseAdapter<Word,ItemsWidgetBinding,WidgetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidgetViewHolder {
        return WidgetViewHolder(ItemsWidgetBinding.inflate(LayoutInflater.from(parent.context),parent,false),listener)
    }

}