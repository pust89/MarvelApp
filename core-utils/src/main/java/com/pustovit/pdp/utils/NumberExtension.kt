package com.pustovit.pdp.utils

private const val LONG_ZERO: Long = 0L
private const val INT_ZERO: Int = 0
private const val DOUBLE_ZERO: Double = 0.0
private const val FLOAT_ZERO: Float = 0.0f

fun Long?.orZero(): Long {
    return this ?: 0L
}

fun Int?.orZero(): Int {
    return this ?: 0
}

fun Double?.orZero(): Double {
    return this ?: 0.0
}

fun Float?.orZero(): Float {
    return this ?: 0.0F
}


fun Long.isZero(): Boolean = this == LONG_ZERO

fun Int.isZero(): Boolean = this == INT_ZERO

fun Double.isZero(): Boolean = this == DOUBLE_ZERO

fun Float.isZero(): Boolean = this == FLOAT_ZERO


fun Long?.isZeroOrNull(): Boolean = this == LONG_ZERO || this == null

fun Int?.isZeroOrNull(): Boolean = this == INT_ZERO || this == null

fun Double?.isZeroOrNull(): Boolean = this == DOUBLE_ZERO || this == null

fun Float?.isZeroOrNull(): Boolean = this == FLOAT_ZERO || this == null