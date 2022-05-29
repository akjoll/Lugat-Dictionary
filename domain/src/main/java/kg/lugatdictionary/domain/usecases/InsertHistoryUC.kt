package kg.lugatdictionary.domain.usecases

import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.repository.MainRepo
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.domain.utils.Either
import kg.lugatdictionary.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

class InsertHistoryUC(private val repo: MainRepo): BaseUseCase<BaseUseCase.None, Word>() {
    override suspend fun run(params: Word): Flow<Either<Failure, None>> {
        return repo.insertHistory(params)
    }
}