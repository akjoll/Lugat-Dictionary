package kg.lugatdictionary.domain.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Type : Any?, in Params> {

    abstract suspend fun run(params: Params): Flow<Either<Failure, Type>>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onResult: (Flow<Either<Failure, Type>>) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                run(params)
            }
            onResult(deferred.await())
        }
    }

    object None
}