package kg.lugatdictionary.vm.utils.base

import android.util.Log
import androidx.lifecycle.*
import kg.lugatdictionary.domain.utils.Either
import kotlinx.coroutines.launch
import kg.lugatdictionary.domain.utils.Failure
import kg.lugatdictionary.domain.utils.onFailure
import kg.lugatdictionary.domain.utils.onSuccess
import kotlinx.coroutines.flow.*

open class BaseVM : ViewModel() {

    protected val _failureLD = MutableLiveData<Failure>()
    val failureLD: LiveData<Failure> = _failureLD
    protected val _loadingLD = MutableLiveData<Boolean>()
    val loadingLD: LiveData<Boolean> = _loadingLD

    protected suspend fun withViewModelScope(fn: suspend () -> Unit) {
        viewModelScope.launch {
            _loadingLD.value = true
            fn()
            _loadingLD.value = false
        }
    }

    protected fun <T> Flow<Either<Failure, T>>.collectResponse( flowResult: MutableSharedFlow<T>, alsoOnSuccess: () -> Unit = { }){
        viewModelScope.launch {
            this@collectResponse.collect { result ->
                result
                    .onSuccess {
                        viewModelScope.launch { flowResult.emit(it)}
                        alsoOnSuccess.invoke()
                    }
                    .onFailure { _failureLD.value = it }
            }
        }

    }
}