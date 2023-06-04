package kg.lugatdictionary.ui.search

import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import kg.lugatdictionary.databinding.FragmentSearchBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.favourite.WordListeners
import kg.lugatdictionary.ui.utils.Timer
import kg.lugatdictionary.ui.utils.base.BaseVMFragment
import kg.lugatdictionary.ui.utils.extensions.withLifecycleScope
import kg.lugatdictionary.ui.utils.getDate
import kg.lugatdictionary.ui.word_detail.WordDetailBottomSheetFragment
import kg.lugatdictionary.vm.LaunchVM
import kg.lugatdictionary.vm.SearchVM
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.util.*

class SearchFragment: BaseVMFragment<FragmentSearchBinding, SearchVM>(), WordListeners {
    override val viewModel: SearchVM by viewModel()
    private val timer by lazy { Timer(lifecycleScope, this::search) }

    private val saveVieModel: LaunchVM by viewModel()

    override fun inflateBinding(inflater: LayoutInflater) = FragmentSearchBinding.inflate(inflater)

    private val adapter by lazy { SearchAdapter(this) }

    override fun init() {
        initRequests()
        initRV()
        initListeners()
        initObservers()
        search()

//        val items = listOf<Word>(
//            Word(0, "Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук 2.Не дей аласын"),
//            Word(1, "Backr","1.Түбөлүктүүлүк, чексиздик, соңсуздук бакр"),
//            Word(2, "Cigitter","1.Түбөлүктүүлүк, чексиздик, соңсуздук жигиттер"),
//            Word(3, "Dogs","1.Түбөлүктүүлүк, чексиздик, соңсуздук иттер")
//        )
//        saveVieModel.saveWords(items)
    }

    private fun initRV() {
        binding.rvWords.adapter=adapter
    }

    private fun initObservers() = with(viewModel) {
        withLifecycleScope(searchResponse) {
            adapter.setData(it)
        }

    }
    private fun initRequests(){
        viewModel.getWords()
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

    override fun onWordClicked(word: Word) {
        WordDetailBottomSheetFragment(word).show(childFragmentManager, null)
        word.date = getDate()
        viewModel.insertHistory(word)
    }

    override fun deleteWord(word: Word) {

    }
}