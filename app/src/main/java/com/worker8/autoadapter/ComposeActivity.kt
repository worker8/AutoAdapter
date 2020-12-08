package com.worker8.autoadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.RecyclerView
import androidx.ui.tooling.preview.Preview
import com.worker8.auto.adapter.library.AutoAdapter

class ComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose)
        val adapter = AutoAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter

        val items = listOf("A", "B", "C")
        setContent {
            RecyclingWithCompose(items)
        }
    }
}

@Preview
@Composable
fun RecyclingWithCompose(list: List<String>) {
    LazyColumnFor(list) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Item is $it")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Hello $name!")
    }
}