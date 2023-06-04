package kg.lugatdictionary.domain.usecases

import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.repository.MainRepo
import kg.lugatdictionary.domain.utils.BaseUseCase

class DeleteWidgetUC(private val repo: MainRepo): BaseUseCase<Boolean, Word>() {
    override suspend fun run(params: Word) =
        repo.deleteWidget(params)
}