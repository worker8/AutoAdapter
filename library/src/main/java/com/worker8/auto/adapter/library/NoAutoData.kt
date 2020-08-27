package com.worker8.auto.adapter.library

import com.worker8.auto.adapter.library.AutoData

class NoAutoData(override val id: Long = -1) : AutoData {
    override fun isContentSame(other: AutoData): Boolean {
        return true
    }
}