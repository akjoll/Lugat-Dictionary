package kg.lugatdictionary.ui.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import kg.lugatdictionary.R

abstract class BaseDialogFragment<VB : ViewBinding> :
    DialogFragment() {

    protected var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.AlertDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater)

        return binding.root
    }

    protected abstract fun inflateBinding(
        inflater: LayoutInflater
    ): VB

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    protected abstract fun init()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
