package com.worker8.autoadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.worker8.auto.adapter.library.AutoAdapter
import kotlinx.android.synthetic.main.activity_basic_list.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val adapter = AutoAdapter(hasStableIds = true)
        recyclerView.adapter = adapter

    }
}
