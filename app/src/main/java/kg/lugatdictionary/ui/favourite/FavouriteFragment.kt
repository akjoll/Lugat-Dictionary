package kg.lugatdictionary.ui.favourite

import android.util.Log
import android.view.LayoutInflater
import kg.lugatdictionary.R
import kg.lugatdictionary.databinding.FragmentFavouriteBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.utils.base.BaseVMFragment
import kg.lugatdictionary.ui.utils.extensions.withLifecycleScope
import kg.lugatdictionary.ui.word_detail.WordDetailBottomSheetFragment
import kg.lugatdictionary.vm.FavoriteVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment: BaseVMFragment<FragmentFavouriteBinding, FavoriteVM>(), WordListeners {
    override val viewModel: FavoriteVM by viewModel()
    override fun inflateBinding(inflater: LayoutInflater) = FragmentFavouriteBinding.inflate(inflater)


    private val adapter by lazy { FavoriteAdapter(this) }

    override fun init() {
        initToolbar()
        initRV()
        initObservers()
        initRequests()
    }

    private fun initRequests() {
        viewModel.getFavorites()
    }

    private fun initObservers() = with(viewModel) {
        withLifecycleScope(favorites){
            adapter.setData(it)
        }
    }

    private fun initRV() {
        binding.rvFavoriteWords.adapter=adapter
    }

    private fun initToolbar() {
        binding.inclToolbar.tvTitle.text = getString(R.string.favourite)
    }

    override fun onWordClicked(word: Word) {
        WordDetailBottomSheetFragment(word).show(childFragmentManager, null)
    }

    override fun deleteWord(word: Word) {
        viewModel.deleteWord(word)
    }

}