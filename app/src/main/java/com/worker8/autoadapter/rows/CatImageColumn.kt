package com.worker8.autoadapter.rows

import android.view.View
import com.bumptech.glide.Glide
import com.worker8.auto.adapter.library.ListItem
import com.worker8.autoadapter.R
import com.worker8.autoadapter.data.ImageData
import kotlinx.android.synthetic.main.horizontal_item.view.*

class CatImageColumn(override val data: ImageData) :
    ListItem<ImageData>() {
    override val layoutResId = R.layout.horizontal_item
    override fun bind(itemView: View) {
        itemView.apply {
            itemText.text = data.text
            Glide.with(itemView.context)
                .load(data.imageUrl)
                .fitCenter()
                .into(itemView.itemImage)
        }
    }
}
