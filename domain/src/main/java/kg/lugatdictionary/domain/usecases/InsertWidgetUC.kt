package kg.lugatdictionary.domain.usecases

import kg.lugatdictionary.domain.entities.Word
import kg.lugatdictionary.domain.repository.MainRepo
import kg.lugatdictionary.domain.utils.BaseUseCase

class InsertWidgetUC(private val repo: MainRepo): BaseUseCase<Boolean, Word>() {
    override suspend fun run(params: Word) = repo.insertWidget(params)
}