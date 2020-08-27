package com.worker8.autoadapter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.worker8.auto.adapter.library.AutoAdapter
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
            SimpleRow(StringData(1L, "BasicListActivity")) {
                startActivity(Intent(this@TopActivity, BasicListActivity::class.java))
            },
            SimpleRow(StringData(1L, "Dummy Example #1")) {},
            SimpleRow(StringData(1L, "Dummy Example #2")) {},
            SimpleRow(StringData(1L, "Dummy Example #3")) {},
        )
        adapter.submitList(list)
    }
}

