package com.worker8.autoadapter.data

import androidx.recyclerview.widget.RecyclerView.NO_ID
import com.worker8.auto.adapter.library.AutoData

data class StringData(override val id: Long = NO_ID, val text: String) : AutoData {
    override fun isContentSame(other: AutoData): Boolean {
        return this == other
    }
}