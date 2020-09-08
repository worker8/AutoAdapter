package com.worker8.auto.adapter.library

import android.view.View
import androidx.annotation.LayoutRes

abstract class ListItem<out T : AutoData> {
    abstract val data: T

    @get:LayoutRes
    abstract val layoutResId: Int
    abstract fun bind(itemView: View)
}