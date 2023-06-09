package kg.lugatdictionary.ui.history

import kg.lugatdictionary.databinding.ItemsHistoryBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.favourite.WordListeners
import kg.lugatdictionary.ui.utils.base.BaseVH

class HistoryViewHolder(binding: ItemsHistoryBinding,private val listener: WordListeners):BaseVH<Word,ItemsHistoryBinding>(binding) {
    override fun bind(item: Word) {
        with(binding){
            tvWord.text = item.word
            tvDate.text = item.date

            itemView.setOnClickListener {
                listener.onWordClicked(item)
            }

            ivDelete.setOnClickListener {
                listener.deleteWord(item)
            }
        }
    }

}