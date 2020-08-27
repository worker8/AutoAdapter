package com.worker8.autoadapter.rows

import com.worker8.auto.adapter.library.AutoData
import com.worker8.autoadapter.data.ImageData

data class ColumnList(override val id: Long, val list: List<ImageData>) : AutoData {
    override fun isContentSame(other: AutoData): Boolean {
        return this == other
    }
}