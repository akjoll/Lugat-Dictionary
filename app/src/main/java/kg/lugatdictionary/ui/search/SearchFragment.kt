package kg.lugatdictionary.ui.search

import android.view.LayoutInflater
import kg.lugatdictionary.databinding.FragmentSearchBinding
import kg.lugatdictionary.domain.Word
import kg.lugatdictionary.ui.utils.base.BaseFragment
import kg.lugatdictionary.ui.utils.extensions.snackbar

class SearchFragment: BaseFragment<FragmentSearchBinding>(), WordsListeners {
    override fun inflateBinding(inflater: LayoutInflater) = FragmentSearchBinding.inflate(inflater)

    private val adapter by lazy { SearchAdapter(this) }

    override fun init() {
        binding.rvWords.adapter=adapter
        val words= listOf<Word>(
            Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"))
        adapter.setData(words)
    }

    override fun onWordClicked(position: Int, title: String) {
        snackbar("Clicked $position $title")
    }
}