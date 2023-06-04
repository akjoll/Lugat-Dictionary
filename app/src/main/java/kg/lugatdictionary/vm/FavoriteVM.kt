package kg.lugatdictionary.vm

import android.util.Log
import androidx.lifecycle.viewModelScope
import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.usecases.DeleteFavoriteUC
import kg.lugatdictionary.domain.usecases.GetFavoritesUC
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.vm.utils.base.BaseVM
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

open class FavoriteVM(
    private val getFavoritesUC: GetFavoritesUC,
    private val deleteFavoriteUC: DeleteFavoriteUC
): BaseVM() {

    private val _favorites = MutableSharedFlow<List<Word>>()
    val favorites: SharedFlow<List<Word>> = _favorites

    fun getFavorites(){
        getFavoritesUC(BaseUseCase.None, viewModelScope){
            it.collectResponse(_favorites)
        }
    }

    fun deleteWord(word: Word){
        deleteFavoriteUC(word, viewModelScope){
            viewModelScope.launch {
                it.collect { getFavorites() }
            }
        }
    }
}