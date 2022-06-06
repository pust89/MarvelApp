package com.pustovit.pdp.marvelapp.common.exception

class LocalException(
    cause: Throwable
) : AppException(cause) {

    companion object {
        fun from(throwable: Throwable): LocalException {
            return LocalException(cause = throwable)
        }
    }
}