package kg.lugatdictionary.ui.utils.base

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import kg.lugatdictionary.R
import kg.lugatdictionary.domain.utils.Failure
import kg.lugatdictionary.ui.utils.Loadable
import kg.lugatdictionary.ui.utils.extensions.snackbar
import kg.lugatdictionary.vm.utils.base.BaseVM

abstract class BaseBottomSheetVMFragment<VB : ViewBinding, VM : BaseVM>
    : BaseBottomSheetDialogFragment<VB>() {

    protected abstract val viewModel: VM
    protected open var isToShowProgress = true

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        initVM()
    }

    private fun initVM() = with(viewModel) {
        loadingLD.observe(viewLifecycleOwner) {
            onLoadingChanged(it)
        }
        failureLD.observe(viewLifecycleOwner) {
            onFailure(it)
        }
    }

    protected open fun onLoadingChanged(isLoading: Boolean) {
        if (isToShowProgress && requireActivity() is Loadable) {
            (requireActivity() as Loadable).onLoadChanged(isLoading)
        }
    }

    protected open fun onFailure(failure: Failure) {
        when (failure) {
            is Failure.Network.NoConnection -> {
                onNetworkFailure(failure)
            }
            is Failure.Server -> {
                onServerError(failure)
            }
            is Failure.Util -> {
                snackbar(failure.message)
            }
            is Failure.Default -> {
            }
        }
    }

    protected open fun onNetworkFailure(
        failure: Failure.Network
    ) = with(failure) {
        snackbar(R.string.no_internet)
    }

    protected open fun onServerError(
        failure: Failure.Server
    ) {
        snackbar(R.string.server_error)
    }
}