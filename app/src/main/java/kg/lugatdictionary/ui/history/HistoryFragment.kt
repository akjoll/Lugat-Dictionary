package kg.lugatdictionary.ui.history

import android.view.LayoutInflater
import kg.lugatdictionary.R
import kg.lugatdictionary.databinding.FragmentHistoryBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.utils.base.BaseFragment
import kg.lugatdictionary.ui.utils.extensions.snackbar

class HistoryFragment: BaseFragment<FragmentHistoryBinding>(),HistoryListener {
    override fun inflateBinding(inflater: LayoutInflater) = FragmentHistoryBinding.inflate(inflater)
    private val adapter by lazy { HistoryAdapter(this) }

    override fun init() {
        binding.inclToolbar.tvTitle.text = getString(R.string.history)
        binding.rvHistory.adapter=adapter
        val words= listOf<Word>(
            Word(0, "Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word(1, "Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word(2, "Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук"),
            Word(3, "Abad","1.Түбөлүктүүлүк, чексиздик, соңсуздук")
        )
        adapter.setData(words)
    }

    override fun onWordClicked(position: Int, title: String) {
        snackbar("Clicked $position $title")
    }
}