package kg.lugatdictionary.ui.favourite

import android.view.LayoutInflater
import kg.lugatdictionary.R
import kg.lugatdictionary.databinding.FragmentFavouriteBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.utils.base.BaseFragment
import kg.lugatdictionary.ui.utils.extensions.snackbar

class FavouriteFragment: BaseFragment<FragmentFavouriteBinding>(),FavoriteListeners {
    override fun inflateBinding(inflater: LayoutInflater) = FragmentFavouriteBinding.inflate(inflater)
    private val adapter by lazy { FavoriteAdapter(this) }


    override fun init() {
        initToolbar()
        binding.rvFavoriteWords.adapter=adapter
        val words = listOf<Word>(
            Word(0, "Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word(1, "Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word(2, "Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word(3, "Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук")
        )
        adapter.setData(words)
    }

    private fun initToolbar() {
        binding.inclToolbar.tvTitle.text = getString(R.string.favourite)
    }

    override fun onWordClicked(position: Int, title: String) {
        snackbar("clicked $position $title")
    }
}