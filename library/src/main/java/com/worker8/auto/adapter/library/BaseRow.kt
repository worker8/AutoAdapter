package com.worker8.auto.adapter.library

import android.view.View
import androidx.annotation.LayoutRes

abstract class BaseRow<AutoData>(open val data: AutoData, @LayoutRes val layoutResId: Int) {
    abstract fun bind(itemView: View)
}