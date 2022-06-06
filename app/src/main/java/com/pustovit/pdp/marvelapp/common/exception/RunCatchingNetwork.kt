package com.pustovit.pdp.marvelapp.common.exception

import com.pustovit.pdp.marvelapp.common.result.mapOnFailure

inline fun <T, R> T.runCatchingNetwork(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        Result.failure(e)
    }.mapNetworkError()
}

fun <T> Result<T>.mapNetworkError(): Result<T> {
    return mapOnFailure { exception ->
        val mappedException = NetworkException.from(exception)
        Result.failure(mappedException)
    }
}