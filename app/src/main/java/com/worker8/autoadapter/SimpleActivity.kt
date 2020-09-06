package com.worker8.autoadapter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.NO_ID
import com.worker8.auto.adapter.library.AutoAdapter
import com.worker8.auto.adapter.library.BaseRow
import com.worker8.autoadapter.data.NormalAutoData
import kotlinx.android.synthetic.main.activity_simple.*
import kotlinx.android.synthetic.main.normal_row.view.*

class SimpleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        val adapter = AutoAdapter(hasStableIds = false)
        recyclerView.adapter = adapter
        val ROW_COUNT = 100
        val list = mutableListOf()

        list.add(
            NormalRow(NormalAutoData(NO_ID, "title #${counter}", "desc #${counter++}"))
        )
        adapter.submitList(list)
    }
}

class NormalRow(override val data: NormalAutoData) :
    BaseRow<NormalAutoData>(data, R.layout.normal_row) {
    override fun bind(itemView: View) {
        itemView.titleText.text = data.name
        itemView.descText.text = data.desc
    }
}