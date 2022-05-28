package kg.lugatdictionary.ui.search

import android.util.Log
import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import kg.lugatdictionary.databinding.FragmentSearchBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.utils.Timer
import kg.lugatdictionary.ui.utils.base.BaseFragment
import kg.lugatdictionary.ui.utils.base.BaseVMFragment
import kg.lugatdictionary.ui.utils.extensions.snackbar
import kg.lugatdictionary.ui.utils.extensions.withLifecycleScope
import kg.lugatdictionary.vm.SearchVM
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: BaseVMFragment<FragmentSearchBinding, SearchVM>(), WordsListeners {
    override val viewModel: SearchVM by viewModel()
    private val timer by lazy { Timer(lifecycleScope, this::search) }

    override fun inflateBinding(inflater: LayoutInflater) = FragmentSearchBinding.inflate(inflater)

    private val adapter by lazy { SearchAdapter(this) }

    override fun init() {
        initRV()
        initListeners()
        initObservers()
        search()
    }

    private fun initRV() {
        binding.rvWords.adapter=adapter
    }

    private fun initObservers() = with(viewModel) {
        withLifecycleScope(searchResponse) {
            adapter.setData(it)
        }
    }

    private fun initListeners() = with(binding) {
        inclToolbar.etSearch.doAfterTextChanged {
            timer.startTimer()
        }
    }

    private fun search(){
        binding.inclToolbar.etSearch.text.toString().run {
            viewModel.search(this)
        }
    }

    override fun onWordClicked(position: Int, title: String) {
        snackbar("Clicked $position $title")
    }
}