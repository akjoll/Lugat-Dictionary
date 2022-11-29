package kg.lugatdictionary.vm

import androidx.lifecycle.viewModelScope
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.usecases.GetWordsBySearchUC
import kg.lugatdictionary.domain.usecases.GetWordsUC
import kg.lugatdictionary.domain.usecases.InsertHistoryUC
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.vm.utils.base.BaseVM
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class SearchVM(
    private val getWordsBySearchUC: GetWordsBySearchUC,
    private val getWordsUC: GetWordsUC,
    private val insertHistoryUC: InsertHistoryUC
) : BaseVM() {

    private val _searchResponse = MutableStateFlow<List<Word>>(listOf())
    val searchResponse: StateFlow<List<Word>> = _searchResponse

    private val _words = MutableSharedFlow<List<Word>>()
    val words: SharedFlow<List<Word>> = _words
    private val _insertedHistory = MutableSharedFlow<BaseUseCase.None>()

    fun search(searchText: String){
        _loadingLD.value = true
        getWordsBySearchUC(searchText, viewModelScope){
            it.collectResponse(_searchResponse){
                _loadingLD.value = false
            }
        }
    }

    fun getWords(){
        getWordsUC(BaseUseCase.None,viewModelScope){
            it.collectResponse(_words)
        }
    }
    fun insertHistory(word: Word){
        insertHistoryUC(word, viewModelScope){
            it.collectResponse(_insertedHistory)
        }
    }
}