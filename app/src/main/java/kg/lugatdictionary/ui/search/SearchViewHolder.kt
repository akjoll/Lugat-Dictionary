package kg.lugatdictionary.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.lugatdictionary.databinding.ItemsRecycleviewSearchBinding
import kg.lugatdictionary.domain.Word
import kg.lugatdictionary.ui.utils.base.BaseVH

class SearchViewHolder(binding: ItemsRecycleviewSearchBinding, private val listeners: WordsListeners): BaseVH<Word, ItemsRecycleviewSearchBinding>(binding) {
    override fun bind(item: Word) {
        with(binding){
            translateOfWord.text=item.word
            explanationOfTranslate.text=item.explanation

            itemView.setOnClickListener {
                listeners.onWordClicked(absoluteAdapterPosition, item.word)
            }
        }
    }

}