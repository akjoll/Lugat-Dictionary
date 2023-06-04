package kg.lugatdictionary.domain.usecases

import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.repository.MainRepo
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.domain.utils.Either
import kg.lugatdictionary.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

class DeleteFavoriteUC(private val repo: MainRepo): BaseUseCase<Boolean, Word>() {
    override suspend fun run(params: Word) =
        repo.deleteFavorite(params)
}