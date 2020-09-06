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
                Intent(this, SimpleActivity::class.java)
                    .putExtra(HAS_STABLE_ID, false)
                    .also { startActivity(it) }
            },
            SimpleRow(StringData(text = "SimpleActivity - has stable id")) {
                Intent(this, SimpleActivity::class.java)
                    .putExtra(HAS_STABLE_ID, true)
                    .also { startActivity(it) }
            },
            SimpleRow(StringData(text = "MultipleViewTypeActivity")) {
                startActivity(Intent(this@TopActivity, MultipleViewTypeActivity::class.java))
            },
            SimpleRow(StringData(text = "ParallaxActivity")) {
                startActivity(Intent(this@TopActivity, ParallaxActivity::class.java))
            },
            SimpleRow(StringData(text = "")) {},
        )
        adapter.submitList(list)
    }
}

