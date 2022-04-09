package kg.lugatdictionary.ui.utils.extensions

import android.view.View
import androidx.activity.addCallback
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


fun Fragment.safeOpenAppInPlayMarket(onException: (Exception) -> Unit) {
    requireActivity().safeOpenAppInPlayMarket(onException)
}

fun Fragment.snackbar(
    message: String,
    root: View? = view,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    if (root == null) {
        requireActivity().snackbar(
            message = message,
            duration = duration
        )
    } else {
        Snackbar.make(root, message, duration).show()
    }
}

fun Fragment.snackbar(
    @StringRes message: Int,
    view: View? = null,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    snackbar(getString(message), view, duration)
}

fun Fragment.backCallback(onBackClicked: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner
    ) {
        onBackClicked()
    }
}

fun <T> Fragment.getNavigationResult(key: String = "result") =
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(
        key
    )

fun <T> Fragment.setNavigationResult(
    result: T,
    key: String = "result"
) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(
        key,
        result
    )
}

fun Fragment.withLifecycleScope(fn: suspend () -> Unit){
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
            fn.invoke()
        }
    }
}



