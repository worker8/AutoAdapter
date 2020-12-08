package com.worker8.autoadapter.rows

import android.view.View
import com.worker8.auto.adapter.library.ListItem
import android.widget.TextView
import com.worker8.autoadapter.data.NormalAutoData
import com.worker8.autoadapter.R

class NormalRow(override val data: NormalAutoData) :
    ListItem<NormalAutoData>() {
    override val layoutResId = R.layout.normal_row
    override fun bind(itemView: View) {
        itemView.apply {
            findViewById<TextView>(R.id.titleText).text = data.name
            findViewById<TextView>(R.id.descText).text = data.desc
        }
    }
}
