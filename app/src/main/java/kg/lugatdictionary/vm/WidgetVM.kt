package kg.lugatdictionary.vm

import androidx.lifecycle.viewModelScope
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.usecases.DeleteWidgetUC
import kg.lugatdictionary.domain.usecases.GetWidgetsUC
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.vm.utils.base.BaseVM
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

open class WidgetVM(
    private val getWidgetsUC: GetWidgetsUC,
    private val deleteWidgetUC: DeleteWidgetUC
): BaseVM() {

    private val _words = MutableSharedFlow<List<Word>>()
    val words: SharedFlow<List<Word>> = _words

    fun getWidgets(){
        getWidgetsUC(BaseUseCase.None, viewModelScope){
            it.collectResponse(_words)
        }
    }

    fun deleteWidget(word: Word){
        deleteWidgetUC(word, viewModelScope){
            viewModelScope.launch {
                it.collect { getWidgets() }
            }
        }
    }

}