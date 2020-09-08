package com.worker8.autoadapter.util

class AutoIncrementingId {
    private var counter: Long = -1L
    fun next() = ++counter
}