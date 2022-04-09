package kg.lugatdictionary.ui.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        onCreateViewSetup(savedInstanceState)

        _binding = inflateBinding(inflater)

        return binding.root
    }

    protected abstract fun inflateBinding(
        inflater: LayoutInflater
    ): VB

    protected open fun onCreateViewSetup(
        savedInstanceState: Bundle?
    ) {
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    abstract fun init()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}