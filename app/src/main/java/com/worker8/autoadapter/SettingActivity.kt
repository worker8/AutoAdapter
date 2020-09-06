package com.worker8.autoadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.NO_ID
import com.worker8.auto.adapter.library.AutoAdapter
import com.worker8.auto.adapter.library.BaseRow
import com.worker8.auto.adapter.library.AutoData
import com.worker8.autoadapter.data.ImageData
import com.worker8.autoadapter.data.NormalAutoData
import com.worker8.autoadapter.rows.*
import kotlinx.android.synthetic.main.activity_basic_list.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val adapter = AutoAdapter(hasStableIds = true)
        recyclerView.adapter = adapter

    }
}
