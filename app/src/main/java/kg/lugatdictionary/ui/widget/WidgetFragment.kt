package kg.lugatdictionary.ui.widget

import android.view.LayoutInflater
import kg.lugatdictionary.R
import kg.lugatdictionary.databinding.FragmentWidgetBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.favourite.WordListeners
import kg.lugatdictionary.ui.utils.base.BaseFragment
import kg.lugatdictionary.ui.utils.base.BaseVMFragment
import kg.lugatdictionary.ui.utils.extensions.snackbar
import kg.lugatdictionary.ui.utils.extensions.withLifecycleScope
import kg.lugatdictionary.ui.word_detail.WordDetailBottomSheetFragment
import kg.lugatdictionary.vm.WidgetVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class WidgetFragment: BaseVMFragment<FragmentWidgetBinding, WidgetVM>(), WordListeners {
    override val viewModel: WidgetVM by viewModel()
    override fun inflateBinding(inflater: LayoutInflater) = FragmentWidgetBinding.inflate(inflater)
    private val adapter by lazy { WidgetAdapter(this) }

    override fun init() {
        initToolbar()
        initRV()
        initObservers()
        initRequests()
    }

    private fun initRequests() {
        viewModel.getWidgets()
    }

    private fun initObservers() = with(viewModel) {
        withLifecycleScope(words){
            adapter.setData(it)
        }
    }

    private fun initRV() {
        binding.rvWidgets.adapter=adapter
    }

    private fun initToolbar() {
        binding.inclToolbar.tvTitle.text = getString(R.string.widget)
    }

    override fun onWordClicked(word: Word) {
        WordDetailBottomSheetFragment(word).show(childFragmentManager, null)
    }

    override fun deleteWord(word: Word) {
        viewModel.deleteWidget(word)
    }
}