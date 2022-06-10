package com.pustovit.pdp.marvelapp.common.extension

import io.reactivex.Single
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.jvm.Throws

/**
 * Wrap [Single.retryWhen] function.
 * Throw NoSuchElementException if number of attempt out of limits.
 */
@Throws
fun <T> Single<T>.smartRetryWhen(
    times: Int = 2,
    delay: Long = 3,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): Single<T> {
    return this.retryWhen { errors ->
        val counter = AtomicInteger()
        errors
            .takeWhile { counter.getAndIncrement() != times }
            .delay(delay, timeUnit)
    }
}