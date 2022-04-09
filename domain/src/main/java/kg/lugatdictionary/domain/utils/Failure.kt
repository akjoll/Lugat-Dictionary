package kg.lugatdictionary.domain.utils

sealed class Failure {

    sealed class Network : Failure() {
        object Unauthorized : Network()
        object NotFound : Network()
        object NoConnection : Network()
        object Default : Network()
    }

    data class Server(
        val code: Int, val message: String? = null
    ) : Failure()

    data class Util(val message: String) : Failure()

    object Default : Failure()
}