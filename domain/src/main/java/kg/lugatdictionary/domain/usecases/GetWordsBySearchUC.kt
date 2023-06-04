package kg.lugatdictionary.domain.usecases

import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.repository.MainRepo
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.domain.utils.Either
import kg.lugatdictionary.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

class GetWordsBySearchUC(private val repo: MainRepo): BaseUseCase<List<Word>, String>() {
    override suspend fun run(params: String): Flow<Either<Failure, List<Word>>> {
        return repo.getWordsBySearch(params)
    }
}
