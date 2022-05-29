package kg.lugatdictionary.domain.usecases

import kg.lugatdictionary.domain.repository.MainRepo
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.domain.utils.Either
import kg.lugatdictionary.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

class CheckFavoriteUC(private val repo: MainRepo): BaseUseCase<Boolean, Long>() {
    override suspend fun run(params: Long): Flow<Either<Failure, Boolean>> {
        return repo.checkFavorite(params)
    }
}