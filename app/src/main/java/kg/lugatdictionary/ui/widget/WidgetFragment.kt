package kg.lugatdictionary.ui.widget

import android.view.LayoutInflater
import kg.lugatdictionary.R
import kg.lugatdictionary.databinding.FragmentWidgetBinding
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.ui.utils.base.BaseFragment
import kg.lugatdictionary.ui.utils.extensions.snackbar

class WidgetFragment: BaseFragment<FragmentWidgetBinding>(), WidgetListener {
    override fun inflateBinding(inflater: LayoutInflater) = FragmentWidgetBinding.inflate(inflater)
    private val adapter by lazy { WidgetAdapter(this) }
    override fun init() {
        binding.inclToolbar.tvTitle.text = getString(R.string.widget)
        binding.rvWidgets.adapter=adapter
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