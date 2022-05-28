package kg.lugatdictionary.vm

import androidx.lifecycle.viewModelScope
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.usecases.GetWordsUC
import kg.lugatdictionary.domain.usecases.SaveWordsUC
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.vm.utils.base.BaseVM
import kotlinx.coroutines.flow.*

class LaunchVM(
    private val getWordsUC: GetWordsUC,
    private val saveWordsUC: SaveWordsUC
): BaseVM() {

    private val _words = MutableSharedFlow<List<Word>>()
    val words: SharedFlow<List<Word>> = _words

    private val _successWordUpdate = MutableSharedFlow<BaseUseCase.None>()
    val successWordUpdate: SharedFlow<BaseUseCase.None> = _successWordUpdate


    fun fetchWords(){
        getWordsUC(BaseUseCase.None, viewModelScope){
            it.collectResponse(_words)
        }
    }

    fun saveWords(words: List<Word>){
        saveWordsUC(words, viewModelScope){
            it.collectResponse(_successWordUpdate)
        }
    }


}