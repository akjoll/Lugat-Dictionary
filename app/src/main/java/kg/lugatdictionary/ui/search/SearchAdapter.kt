package kg.lugatdictionary.ui.search

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import kg.lugatdictionary.databinding.ItemsRecycleviewSearchBinding
import kg.lugatdictionary.domain.Word
import kg.lugatdictionary.ui.utils.base.BaseAdapter

class SearchAdapter(private val listeners: WordsListeners): BaseAdapter<Word,ItemsRecycleviewSearchBinding,SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemsRecycleviewSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false), listeners)
    }


}