package kg.lugatdictionary.ui.history

import android.util.Log
import android.view.LayoutInflater
import kg.lugatdictionary.R
import kg.lugatdictionary.databinding.FragmentHistoryBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.favourite.WordListeners
import kg.lugatdictionary.ui.utils.base.BaseFragment
import kg.lugatdictionary.ui.utils.base.BaseVMFragment
import kg.lugatdictionary.ui.utils.extensions.snackbar
import kg.lugatdictionary.ui.utils.extensions.withLifecycleScope
import kg.lugatdictionary.ui.word_detail.WordDetailBottomSheetFragment
import kg.lugatdictionary.vm.HistoryVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseVMFragment<FragmentHistoryBinding, HistoryVM>(), WordListeners {
    override val viewModel: HistoryVM by viewModel()
    override fun inflateBinding(inflater: LayoutInflater) = FragmentHistoryBinding.inflate(inflater)

    private val adapter by lazy { HistoryAdapter(this) }

    override fun init() {
        initToolbar()
        initRV()
        initObservers()
        initRequests()
    }

    private fun initRequests() {
        viewModel.getHistories()
    }

    private fun initObservers() = with(viewModel) {
        withLifecycleScope(histories){
            adapter.setData(it)
        }
    }

    private fun initRV() {
        binding.rvHistory.adapter = adapter
    }

    private fun initToolbar() {
        binding.inclToolbar.tvTitle.text = getString(R.string.history)
    }

    override fun onWordClicked(word: Word) {
        WordDetailBottomSheetFragment(word).show(childFragmentManager, null)
    }

    override fun deleteWord(word: Word) {
        viewModel.deleteHistory(word)
    }

}