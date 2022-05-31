package kg.lugatdictionary.ui.favourite

import kg.lugatdictionary.databinding.ItemFavoriteBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.utils.base.BaseVH

class FavoriteViewHolder(binding: ItemFavoriteBinding, private val listeners: WordListeners,):BaseVH<Word,ItemFavoriteBinding>(binding) {
    override fun bind(item: Word) {
        with(binding){
            tvTransalteOfWord.text=item.word
            tvExplanationOfTranslate.text=item.explanation

            iconDelete.setOnClickListener {
                listeners.deleteWord(item)
            }
            itemView.setOnClickListener{
                listeners.onWordClicked(item)
            }

        }
    }

}