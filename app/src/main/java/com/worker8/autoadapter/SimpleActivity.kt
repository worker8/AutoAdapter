package com.worker8.autoadapter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.NO_ID
import com.worker8.auto.adapter.library.AutoAdapter
import com.worker8.auto.adapter.library.AutoData
import com.worker8.auto.adapter.library.BaseRow
import com.worker8.autoadapter.data.NormalAutoData
import kotlinx.android.synthetic.main.activity_simple.*
import kotlinx.android.synthetic.main.normal_row.view.*

class SimpleActivity : AppCompatActivity() {
    companion object {
        val HAS_STABLE_ID = "HAS_STABLE_ID"
    }

    private val hasStableId by lazy {
        intent.getBooleanExtra(HAS_STABLE_ID, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        val adapter = AutoAdapter(hasStableIds = hasStableId)
        recyclerView.adapter = adapter
        val ROW_COUNT = 100
        val list = mutableListOf<BaseRow<out AutoData>>()
        for (i in 0 until ROW_COUNT) {
            if (hasStableId) {
                list.add(NormalRow(NormalAutoData(i.toLong(), "title #${i}", "desc #${i}")))
            } else {
                list.add(NormalRow(NormalAutoData(NO_ID, "title #${i}", "desc #${i}")))
            }
        }
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