package com.pustovit.pdp.marvelapp.common.result

typealias UnitResult = Result<Unit>

fun unitSuccess() = Result.success(Unit)

fun <T : Any> success(value: T): Result<T> = Result.success(value)

fun <T : Any> fail(error: Throwable): Result<T> = Result.failure(error)

inline fun <I, O> Result<I>.mapOnSuccess(transform: (I) -> Result<O>): Result<O> {
    val value = getOrElse {
        return Result.failure(exception = it)
    }
    return transform(value)
}

inline fun <I> Result<I>.mapOnFailure(transform: (error: Throwable) -> Result<I>): Result<I> {
    return exceptionOrNull()?.let(transform) ?: this
}


@Suppress("UNCHECKED_CAST")
fun <T1, T2, R> Result<T1>.merge(result: Result<T2>, merger: (T1, T2) -> R): Result<R> {
    val exceptionFirst = exceptionOrNull()
    val exceptionSecond = result.exceptionOrNull()
    val valueFirst = getOrNull()
    val valueSecond = result.getOrNull()
    return when {
        exceptionFirst != null -> Result.failure(exceptionFirst)
        exceptionSecond != null -> Result.failure(exceptionSecond)
        else -> runCatching { merger((valueFirst as T1), (valueSecond as T2)) }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> Result<T>.merge(result: Result<T>): Result<T> {
    val exceptionFirst = exceptionOrNull()
    val exceptionSecond = result.exceptionOrNull()
    return when {
        exceptionFirst != null -> Result.failure(exceptionFirst)
        exceptionSecond != null -> Result.failure(exceptionSecond)
        else -> result
    }
}

