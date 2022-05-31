package kg.lugatdictionary.vm

import androidx.lifecycle.viewModelScope
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.usecases.*
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.vm.utils.base.BaseVM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WordDetailVM(
    private val word: Word,
    private val checkWidgetUC: CheckWidgetUC,
    private val checkFavoriteUC: CheckFavoriteUC,
    private val deleteWidgetUC: DeleteWidgetUC,
    private val deleteFavoriteUC: DeleteFavoriteUC,
    private val insertWidgetUC: InsertWidgetUC,
    private val insertFavoriteUC: InsertFavoriteUC,
    private val getWidgetsUC: GetWidgetsUC
): BaseVM(){

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    private val _isWidget = MutableStateFlow(false)
    val isWidget: StateFlow<Boolean> = _isWidget

    private val _widgets = MutableStateFlow<List<Word>>(listOf())

    fun checkIsFavorite(){
        _loadingLD.value = true
        checkFavoriteUC(word.id, viewModelScope){
            it.collectResponse(_isFavorite){
                _loadingLD.value = false
            }
        }
    }

    fun checkIsWidget(){
        _loadingLD.value = true
        checkWidgetUC(word.id, viewModelScope){
            it.collectResponse(_isWidget){
                _loadingLD.value = false
            }
        }
    }

    fun deleteFavorite(){
        deleteFavoriteUC(word, viewModelScope){
            it.collectResponse(_isFavorite)
        }
    }

    fun deleteWidget(){
        deleteWidgetUC(word, viewModelScope){
            it.collectResponse(_isWidget)
        }
    }

    fun insertFavorite(){
        insertFavoriteUC(word, viewModelScope){
            it.collectResponse(_isFavorite)

        }
    }

    fun insertWidget(){
        checkWidgets()
    }

    private fun checkWidgets(){
        getWidgetsUC(BaseUseCase.None, viewModelScope){
            if (_widgets.value.size < 5){
                it.collectResponse(_widgets){
                    insertWidgetUC(word, viewModelScope){
                        it.collectResponse(_isWidget)
                    }
                }
            }
        }
    }


}