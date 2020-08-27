package com.worker8.auto.adapter.library

interface AutoData {
    val id: Long
    fun isContentSame(other: AutoData): Boolean
}