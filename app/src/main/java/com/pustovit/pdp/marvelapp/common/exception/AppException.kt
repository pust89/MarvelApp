package com.pustovit.pdp.marvelapp.common.exception

sealed class AppException(
    cause: Throwable? = null
) : Exception(cause)

