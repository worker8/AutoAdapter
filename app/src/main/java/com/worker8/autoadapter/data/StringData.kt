package com.worker8.autoadapter.data

import com.worker8.auto.adapter.library.AutoData

data class StringData(override val id: Long, val text: String) : AutoData {
    override fun isContentSame(other: AutoData): Boolean {
        return this == other
    }
}