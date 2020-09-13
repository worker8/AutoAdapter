package com.worker8.autoadapter.rows

import android.view.View
import android.widget.TextView
import com.worker8.auto.adapter.library.AutoData
import com.worker8.auto.adapter.library.ListItem
import com.worker8.autoadapter.R

class FooterRow(override val data: Data) :
    ListItem<FooterRow.Data>() {
    override val layoutResId = R.layout.footer_row
    override fun bind(itemView: View) {
        itemView.findViewById<TextView>(R.id.footerText).text = data.privacyMessage
    }

    data class Data(override val id: Long, val privacyMessage: String) : AutoData {
        override fun isContentSame(other: AutoData): Boolean {
            return this == other
        }
    }
}