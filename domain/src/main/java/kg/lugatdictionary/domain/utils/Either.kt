package kg.lugatdictionary.domain.utils

sealed class Either<out L, out R> {

    /** by convention Left represents failure */
    data class Left<out L>(val l: L) : Either<L, Nothing>()

    /** by convention Right represents success */
    data class Right<out R>(val r: R) : Either<Nothing, R>()

    val isLeft get() = this is Left<L>
    val isRight get() = this is Right<R>

    fun <L> left(l: L) = Left(l)
    fun <R> right(r: R) = Right(r)

    fun fold(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(l)
            is Right -> fnR(r)
        }
}

fun <A, B, C> ((A) -> B).c(fn: (B) -> C): (A) -> C = {
    fn(this(it))
}

fun <T, L, R> Either<L, R>.flatMap(
    fn: (R) -> Either<L, T>
): Either<L, T> =
    when (this) {
        is Either.Left -> left(l)
        is Either.Right -> fn(r)
    }

fun <T, L, R> Either<L, R>.map(fn: (R) -> T): Either<L, T> =
    this.flatMap(fn.c(::right))

fun <L, R> Either<L, R>.getOrElse(value: R): R =
    when (this) {
        is Either.Left -> value
        is Either.Right -> r
    }

fun <L, R> Either<L, R>.onFailure(
    fn: (failure: L) -> Unit
): Either<L, R> = apply { if (this is Either.Left) fn(l) }

fun <L, R> Either<L, R>.onSuccess(
    fn: (success: R) -> Unit
): Either<L, R> = apply { if (this is Either.Right) fn(r) }