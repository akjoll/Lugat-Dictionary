package kg.lugatdictionary.ui.favourite

import android.view.LayoutInflater
import kg.lugatdictionary.databinding.FragmentFavouriteBinding
import kg.lugatdictionary.domain.Word
import kg.lugatdictionary.ui.utils.base.BaseFragment
import kg.lugatdictionary.ui.utils.extensions.snackbar

class FavouriteFragment: BaseFragment<FragmentFavouriteBinding>(),FavoriteListeners {
    override fun inflateBinding(inflater: LayoutInflater) = FragmentFavouriteBinding.inflate(inflater)
    private val adapter by lazy { FavoriteAdapter(this) }


    override fun init() {
        binding.rvFavoriteWords.adapter=adapter
        val words = listOf<Word>(
        Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
        Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
        Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
        Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"))
        adapter.setData(words)
    }

    override fun onWordClicked(position: Int, title: String) {
        snackbar("clicked $position $title")
    }
}