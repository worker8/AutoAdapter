package com.worker8.autoadapter.rows

import android.view.View
import com.worker8.auto.adapter.library.ListItem
import android.widget.TextView
import com.worker8.autoadapter.R
import com.worker8.autoadapter.data.StringData

class SimpleRow(
    override val data: StringData,
    val onClick: () -> Unit
) : ListItem<StringData>() {
    override val layoutResId = R.layout.simple_row
    override fun bind(itemView: View) {
        itemView.apply {
            val simpleText = findViewById<TextView>(R.id.simpleText)
            simpleText.text = data.text
            setOnClickListener { onClick() }
        }
    }
}