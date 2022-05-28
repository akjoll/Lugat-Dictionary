package kg.lugatdictionary.vm

import androidx.lifecycle.viewModelScope
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.usecases.GetWordsBySearchUC
import kg.lugatdictionary.vm.utils.base.BaseVM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchVM(
    private val getWordsBySearchUC: GetWordsBySearchUC
) : BaseVM() {

    private val _searchResponse = MutableStateFlow<List<Word>>(listOf())
    val searchResponse: StateFlow<List<Word>> = _searchResponse

    fun search(searchText: String){
        _loadingLD.value = true
        getWordsBySearchUC(searchText, viewModelScope){
            it.collectResponse(_searchResponse){
                _loadingLD.value = false
            }
        }
    }
}