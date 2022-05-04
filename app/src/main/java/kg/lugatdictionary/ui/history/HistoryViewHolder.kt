package kg.lugatdictionary.ui.history

import kg.lugatdictionary.databinding.ItemsHistoryBinding
import kg.lugatdictionary.domain.Word
import kg.lugatdictionary.ui.utils.base.BaseVH

class HistoryViewHolder(binding: ItemsHistoryBinding,private val listener: HistoryListener):BaseVH<Word,ItemsHistoryBinding>(binding) {
    override fun bind(item: Word) {
        with(binding){
            tvWord.text=item.word
            tvDate.text=item.explanation

            itemView.setOnClickListener(){
                listener.onWordClicked(absoluteAdapterPosition,item.word)
            }
        }
    }

}