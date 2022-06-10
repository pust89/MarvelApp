package com.pustovit.pdp.marvelapp.common.result

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Fail<T>(val exception: Throwable) : Result<T>()
}

fun <T> success(data: T): Result<T> {
    return Result.Success<T>(data)
}

fun <T> fail(exception: Throwable): Result<T> {
    return Result.Fail<T>(exception)
}