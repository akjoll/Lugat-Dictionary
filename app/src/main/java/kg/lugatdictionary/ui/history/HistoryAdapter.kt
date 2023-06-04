package kg.lugatdictionary.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import kg.lugatdictionary.databinding.ItemsHistoryBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.favourite.WordListeners
import kg.lugatdictionary.ui.utils.base.BaseAdapter

class HistoryAdapter(private val listener: WordListeners):BaseAdapter<Word,ItemsHistoryBinding,HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(ItemsHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false),listener)
    }

}