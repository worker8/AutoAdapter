package com.worker8.autoadapter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.worker8.auto.adapter.library.AutoAdapter
import com.worker8.auto.adapter.library.AutoData
import com.worker8.auto.adapter.library.BaseRow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.normal_row.view.*
import kotlinx.android.synthetic.main.simple_row.view.*

class TopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)
        val adapter = AutoAdapter()
        recyclerView.adapter = adapter

        val list = listOf(
            SimpleRow(StringData(1L, "Simple List")) {
                startActivity(Intent(this@TopActivity, MainActivity::class.java))
            },
            SimpleRow(StringData(1L, "Example 1")) {},
            SimpleRow(StringData(1L, "Example 2")) {},
            SimpleRow(StringData(1L, "Example 3")) {},
        )
        adapter.submitList(list)
    }
}

private class SimpleRow(override val data: StringData, val onClick: () -> Unit) :
    BaseRow<StringData>(data, R.layout.simple_row) {
    override fun bind(itemView: View) {
        itemView.apply {
            simpleText.text = data.text
            setOnClickListener { onClick() }
        }

    }
}

private data class StringData(override val id: Long, val text: String) : AutoData {
    override fun isContentSame(other: AutoData): Boolean {
        return this == other
    }
}

