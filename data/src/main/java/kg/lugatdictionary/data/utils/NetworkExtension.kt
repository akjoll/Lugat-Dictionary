package kg.lugatdictionary.data.utils

import android.util.Log
import kg.lugatdictionary.domain.utils.Either
import kg.lugatdictionary.domain.utils.Failure
import retrofit2.Response

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun <T> service(
    request: suspend () -> Response<T>
): Either<Failure, T> =
    try {
        val response = request()
        if (response.isSuccessful && response.body() != null) {
            Either.Right(response.body()!!)
        } else {
            Either.Left(getFailure(response))
        }
    } catch (e: Exception) {
        Log.e("NetworkExt", e.message.toString())

        Either.Left(Failure.Network.NoConnection)
    }

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun <T> serviceExceptBaseResponse(
    request: suspend () -> Response<T>
): Either<Failure, T> =
    try {
        val response = request()
        if (response.isSuccessful && response.body() != null) {
            Either.Right(response.body()!!)
        } else {
            Either.Left(getFailure(response))
        }
    } catch (e: Exception) {
        Log.e("NetworkExt", e.message.toString())

        Either.Left(Failure.Network.NoConnection)
    }

private fun <T> getFailure(response: Response<T>): Failure =
    when {
        response.code() / 100 == 5 -> Failure.Server(response.code())
        response.code() == 401 -> Failure.Network.Unauthorized
        response.code() == 404 -> Failure.Network.NotFound
        response.code() / 100 == 4 -> Failure.Network.Default
        else -> {
            if (response.errorBody() != null) {
                Failure.Util(
                    response.errorBody()!!.string()
                )
            } else {
                Failure.Default
            }
        }
    }.also { Log.e("code", "${response.code()}") }
