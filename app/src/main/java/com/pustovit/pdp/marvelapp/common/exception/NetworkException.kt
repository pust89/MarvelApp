package com.pustovit.pdp.marvelapp.common.exception

import kotlinx.coroutines.TimeoutCancellationException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

sealed class NetworkException(
    cause: Throwable? = null
) : AppException(cause) {

    /**
     * 400 @see [code]
     */
    data class BadRequest(val body: Map<String, String> = emptyMap()) : NetworkException()

    /**
     * 401 - Неавторизованный запрос
     */
    class Unauthorized : NetworkException()

    /**
     * 404 - Не найдено
     */
    class NotFound : NetworkException()

    /**
     * 422 - Не удалось обработать содержимое запроса
     */
    class HandlingRequestError : NetworkException()

    /**
     * 500 - Ошибка сервера
     */
    class ServerError : NetworkException()

    class NoInternet : NetworkException()

    class TimeoutError : NetworkException()

    class UnknownError(cause: HttpException?) : NetworkException(cause = cause)

    companion object {

        fun from(throwable: Throwable): NetworkException {
            return when (throwable) {
                is java.net.SocketTimeoutException,
                is TimeoutException,
                is TimeoutCancellationException -> {
                    NetworkException.TimeoutError()
                }
                is UnknownHostException,
                is SocketException,
                is ConnectException -> {
                    NetworkException.NoInternet()
                }
                is HttpException -> {
                    when (throwable.code()) {
                        400 -> {
                            try {
                                val string = throwable.response()?.errorBody()?.string().orEmpty()
                                val json = JSONObject(string)
                                val body = json.keys()
                                    .asSequence()
                                    .map { key -> key to json.get(key).toString() }
                                    .toMap()
                                NetworkException.BadRequest(body)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                                NetworkException.BadRequest()
                            }
                        }
                        401 -> {
                            NetworkException.Unauthorized()
                        }
                        404 -> {
                            NetworkException.NotFound()
                        }
                        422 -> {
                            NetworkException.HandlingRequestError()
                        }
                        500 -> {
                            NetworkException.ServerError()
                        }
                        else -> {
                            NetworkException.UnknownError(throwable)
                        }
                    }
                }
                else -> NetworkException.UnknownError(null)
            }
        }
    }

}
