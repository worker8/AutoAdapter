package com.worker8.autoadapter.data

import com.worker8.auto.adapter.library.AutoData

data class ImageData(override val id: Long, val text: String, val imageUrl: String) : AutoData {
    override fun isContentSame(other: AutoData): Boolean {
        return this == other
    }
}