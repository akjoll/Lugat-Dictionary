package kg.lugatdictionary.domain.usecases

import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.repository.MainRepo
import kg.lugatdictionary.domain.utils.BaseUseCase
import kg.lugatdictionary.domain.utils.Either
import kg.lugatdictionary.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

class GetWidgetsUC(private val repo: MainRepo): BaseUseCase<List<Word>, BaseUseCase.None>() {
    override suspend fun run(params: None): Flow<Either<Failure, List<Word>>> {
        return repo.getWidgets()
    }
}