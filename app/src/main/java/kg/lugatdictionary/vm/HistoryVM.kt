package kg.lugatdictionary.vm

import androidx.lifecycle.viewModelScope
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.usecases.DeleteHistoryUC
import kg.lugatdictionary.domain.usecases.GetHistoriesUC
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.vm.utils.base.BaseVM
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryVM(
    private val getHistoriesUC: GetHistoriesUC,
    private val deleteHistoryUC: DeleteHistoryUC
): BaseVM() {

    private val _histories = MutableSharedFlow<List<Word>>()
    val histories: SharedFlow<List<Word>> = _histories

    fun getHistories(){
        getHistoriesUC(BaseUseCase.None, viewModelScope){
            it.collectResponse(_histories)
        }
    }

    fun deleteHistory(word: Word) {
        deleteHistoryUC(word, viewModelScope){
            viewModelScope.launch {
                it.collect { getHistories() }
            }
        }
    }

}