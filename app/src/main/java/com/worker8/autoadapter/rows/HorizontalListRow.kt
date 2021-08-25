package com.worker8.autoadapter.rows

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.worker8.auto.adapter.library.AutoAdapter
import com.worker8.auto.adapter.library.ListItem
import com.worker8.autoadapter.R
import com.worker8.autoadapter.data.ColumnListData

class HorizontalListRow(override val data: ColumnListData) :
    ListItem<ColumnListData>() {
    override val layoutResId = R.layout.horizontal_list
    override fun bind(itemView: View, position: Int) {
        val adapter = AutoAdapter()
        val horizontalRecyclerView =
            itemView.findViewById<RecyclerView>(R.id.horizontalRecyclerView)
        horizontalRecyclerView.adapter = adapter
        val list = data.list.map { item ->
            CatImageColumn(item)
        }
        adapter.submitList(list)
    }
}