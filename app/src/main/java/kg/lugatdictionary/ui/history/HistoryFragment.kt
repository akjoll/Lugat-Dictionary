package kg.lugatdictionary.ui.history

import android.view.LayoutInflater
import kg.lugatdictionary.R
import kg.lugatdictionary.databinding.FragmentHistoryBinding
import kg.lugatdictionary.ui.utils.base.BaseFragment

class HistoryFragment: BaseFragment<FragmentHistoryBinding>() {
    override fun inflateBinding(inflater: LayoutInflater) = FragmentHistoryBinding.inflate(inflater)

    override fun init() {
        binding.inclToolbar.tvTitle.text = getString(R.string.history)
    }
}