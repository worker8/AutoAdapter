package com.worker8.autoadapter.rows

import android.view.View
import com.worker8.auto.adapter.library.ListItem
import com.worker8.autoadapter.data.NormalAutoData
import com.worker8.autoadapter.R
import kotlinx.android.synthetic.main.normal_row.view.*

class NormalRow(override val data: NormalAutoData) :
    ListItem<NormalAutoData>() {
    override val layoutResId = R.layout.normal_row
    override fun bind(itemView: View) {
        itemView.titleText.text = data.name
        itemView.descText.text = data.desc
    }
}
