package kg.lugatdictionary.ui.widget

import kg.lugatdictionary.databinding.ItemsWidgetBinding
import kg.lugatdictionary.domain.Word
import kg.lugatdictionary.ui.utils.base.BaseVH

class WidgetViewHolder(binding: ItemsWidgetBinding,private val listener: WidgetListener):BaseVH<Word,ItemsWidgetBinding>(binding) {
    override fun bind(item: Word) {
        with(binding){
            tvWord.text=item.word
            tvExplanationOfWord.text=item.explanation

            itemView.setOnClickListener(){
                listener.onWordClicked(absoluteAdapterPosition,item.word)
            }
        }
    }

}