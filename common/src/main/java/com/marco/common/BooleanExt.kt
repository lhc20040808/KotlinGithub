package com.marco.common

sealed class BooleanExt<out T>
object Otherwise : BooleanExt<Nothing>()
class WithData<T>(val data: T) : BooleanExt<T>()

inline fun <T> Boolean.yes(block: () -> T): BooleanExt<T> {
    return when {
        this -> WithData(block())
        else -> Otherwise
    }
}

inline fun <T> Boolean.no(block: () -> T): BooleanExt<T> {
    return when {
        !this -> WithData(block())
        else -> Otherwise
    }
}

inline fun <T> BooleanExt<T>.otherwise(block: () -> T): T {
    return when (this) {
        is Otherwise -> block()
        is WithData -> this.data
    }
}

fun main() {
    val ret2 = false.yes {
        1
    }.otherwise {
        2
    }
}