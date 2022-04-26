package com.kooky.utilities

fun <T> List<T>.doIf(predicate: List<T>.() -> Boolean?, block: List<T>.() -> List<T>): List<T> {
    return if (predicate(this) == true) block(this) else this
}