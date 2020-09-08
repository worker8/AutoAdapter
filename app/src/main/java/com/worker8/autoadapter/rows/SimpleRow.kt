package com.worker8.autoadapter.rows

import android.view.View
import com.worker8.auto.adapter.library.BaseRow
import com.worker8.autoadapter.R
import com.worker8.autoadapter.data.StringData
import kotlinx.android.synthetic.main.simple_row.view.*

class SimpleRow(
    override val data: StringData,
    val onClick: () -> Unit
) : BaseRow<StringData>() {
    override val layoutResId = R.layout.simple_row
    override fun bind(itemView: View) {
        itemView.apply {
            simpleText.text = data.text
            setOnClickListener { onClick() }
        }
    }
}