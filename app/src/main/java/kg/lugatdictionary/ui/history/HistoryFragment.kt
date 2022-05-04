package kg.lugatdictionary.ui.history

import android.view.LayoutInflater
import kg.lugatdictionary.R
import kg.lugatdictionary.databinding.FragmentHistoryBinding
import kg.lugatdictionary.domain.Word
import kg.lugatdictionary.ui.utils.base.BaseFragment
import kg.lugatdictionary.ui.utils.extensions.snackbar

class HistoryFragment: BaseFragment<FragmentHistoryBinding>(),HistoryListener {
    override fun inflateBinding(inflater: LayoutInflater) = FragmentHistoryBinding.inflate(inflater)
    private val adapter by lazy { HistoryAdapter(this) }

    override fun init() {
        binding.rvHistory.adapter=adapter
        val words= listOf<Word>(
            Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word("Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук")
        )
        adapter.setData(words)
    }

    override fun onWordClicked(position: Int, title: String) {
        snackbar("Clicked $position $title")
    }
}