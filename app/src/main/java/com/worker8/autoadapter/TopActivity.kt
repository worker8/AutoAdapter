package com.worker8.autoadapter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.worker8.auto.adapter.library.AutoAdapter
import com.worker8.autoadapter.SimpleActivity.Companion.HAS_STABLE_ID
import com.worker8.autoadapter.data.StringData
import com.worker8.autoadapter.rows.SimpleRow
import kotlinx.android.synthetic.main.activity_top.*

class TopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)
        val adapter = AutoAdapter()
        recyclerView.adapter = adapter

        val list = listOf(
            SimpleRow(StringData(text = "SimpleActivity - no stable id")) {
                startActivity(
                    Intent(this@TopActivity, SimpleActivity::class.java).putExtra(
                        HAS_STABLE_ID, false
                    )
                )
            },
            SimpleRow(StringData(text = "SimpleActivity - has stable id")) {
                startActivity(
                    Intent(this@TopActivity, SimpleActivity::class.java).putExtra(
                        HAS_STABLE_ID, true
                    )
                )
            },
            SimpleRow(StringData(text = "BasicListActivity")) {
                startActivity(Intent(this@TopActivity, BasicListActivity::class.java))
            },
            SimpleRow(StringData(text = "ShuffleRearrangeActivity - HasStableId")) {},
            SimpleRow(StringData(text = "")) {},
        )
        adapter.submitList(list)
    }
}

