package com.worker8.autoadapter.data

import android.util.Log
import com.worker8.auto.adapter.library.AutoData

data class NormalAutoData(override val id: Long, val name: String, val desc: String) : AutoData {
    override fun isContentSame(other: AutoData): Boolean {
        return this == other
    }
}