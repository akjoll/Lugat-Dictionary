package kg.lugatdictionary.ui.widget

import android.view.LayoutInflater
import kg.lugatdictionary.databinding.FragmentWidgetBinding
import kg.lugatdictionary.ui.utils.base.BaseFragment

class WidgetFragment: BaseFragment<FragmentWidgetBinding>() {
    override fun inflateBinding(inflater: LayoutInflater) = FragmentWidgetBinding.inflate(inflater)

    override fun init() {

    }
}