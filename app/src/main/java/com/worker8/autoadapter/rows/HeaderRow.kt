package com.worker8.autoadapter.rows

import android.view.View
import com.worker8.auto.adapter.library.ListItem
import com.worker8.auto.adapter.library.NoAutoData
import com.worker8.autoadapter.R
import kotlinx.android.synthetic.main.header_row.view.*

class HeaderRow(private val title: String) :
    ListItem<NoAutoData>() {
    override val layoutResId = R.layout.header_row
    override val data = NoAutoData()
    override fun bind(itemView: View) {
        itemView.headerText.text = title
    }
}