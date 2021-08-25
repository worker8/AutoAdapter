package com.worker8.autoadapter.rows

import android.view.View
import com.worker8.auto.adapter.library.ListItem
import android.widget.TextView
import com.worker8.auto.adapter.library.NoAutoData
import com.worker8.autoadapter.R

class HeaderRow(private val title: String) :
    ListItem<NoAutoData>() {
    override val layoutResId = R.layout.header_row
    override val data = NoAutoData()
    override fun bind(itemView: View, position: Int) {
        itemView.apply {
            findViewById<TextView>(R.id.headerText).text = title
        }
    }
}