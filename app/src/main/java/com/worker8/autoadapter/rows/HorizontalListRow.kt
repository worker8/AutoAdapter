package com.worker8.autoadapter.rows

import android.view.View
import com.worker8.auto.adapter.library.AutoAdapter
import com.worker8.auto.adapter.library.ListItem
import com.worker8.autoadapter.R
import com.worker8.autoadapter.data.ColumnListData
import kotlinx.android.synthetic.main.horizontal_list.view.*

class HorizontalListRow(override val data: ColumnListData) :
    ListItem<ColumnListData>() {
    override val layoutResId = R.layout.horizontal_list
    override fun bind(itemView: View) {
        val adapter = AutoAdapter()
        itemView.horizontalRecyclerView.adapter = adapter
        val list = data.list.map { item ->
            CatImageColumn(item)
        }
        adapter.submitList(list)
    }
}