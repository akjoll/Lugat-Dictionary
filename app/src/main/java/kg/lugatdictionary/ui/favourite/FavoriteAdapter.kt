package kg.lugatdictionary.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import kg.lugatdictionary.databinding.ItemFavoriteBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.utils.base.BaseAdapter

class FavoriteAdapter(private val listeners:WordListeners):
    BaseAdapter<Word, ItemFavoriteBinding, FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false),listeners)
    }

}