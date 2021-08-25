package com.worker8.autoadapter.rows

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.worker8.auto.adapter.library.ListItem
import com.worker8.autoadapter.R
import com.worker8.autoadapter.data.ImageData

class CatImageColumn(override val data: ImageData) :
    ListItem<ImageData>() {
    override val layoutResId = R.layout.horizontal_item
    override fun bind(itemView: View, position: Int) {
        itemView.apply {
            val itemText = findViewById<TextView>(R.id.itemText)
            val itemImage = findViewById<ImageView>(R.id.itemImage)
            itemText.text = data.text
            Glide.with(itemView.context)
                .load(data.imageUrl)
                .fitCenter()
                .into(itemImage)
        }
    }
}
