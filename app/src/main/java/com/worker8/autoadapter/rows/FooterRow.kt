package com.worker8.autoadapter.rows

import android.view.View
import com.worker8.auto.adapter.library.BaseRow
import com.worker8.auto.adapter.library.NoAutoData
import com.worker8.autoadapter.R
import kotlinx.android.synthetic.main.footer_row.view.*

class FooterRow(private val privacyMessage: String) :
    BaseRow<NoAutoData>() {
    override val data = NoAutoData()
    override val layoutResId = R.layout.footer_row
    override fun bind(itemView: View) {
        itemView.footerText.text = privacyMessage
    }
}