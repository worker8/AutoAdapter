package com.worker8.autoadapter

import android.util.Log
import com.worker8.auto.adapter.library.AutoData

data class NormalAutoData(override val id: Long, val name: String, val desc: String) : AutoData {
    override fun isContentSame(other: AutoData): Boolean {
        Log.d("ddw", "---- isContentSame ----");
        Log.d("ddw", "$this");
        Log.d("ddw", "$other");
        return this == other
    }
}