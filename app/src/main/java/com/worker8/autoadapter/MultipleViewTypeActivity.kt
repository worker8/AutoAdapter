package com.worker8.autoadapter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.worker8.auto.adapter.library.AutoAdapter
import com.worker8.auto.adapter.library.AutoData
import com.worker8.auto.adapter.library.ListItem
import com.worker8.autoadapter.data.ColumnListData
import com.worker8.autoadapter.data.ImageData
import com.worker8.autoadapter.data.NormalAutoData
import com.worker8.autoadapter.rows.*
import com.worker8.autoadapter.util.AutoIncrementingId

class MultipleViewTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_list)
        val adapter = AutoAdapter(hasStableIds = false)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter

        var counter = 5
        val autoIncrementingId = AutoIncrementingId()
        val horizontalList = ColumnListData(
            autoIncrementingId.next(), listOf(
                ImageData(1L, "Cat #1", "https://i.imgur.com/KF9tsGy.png"),
                ImageData(2L, "Cat #2", "https://i.imgur.com/S04Ocnv.png"),
                ImageData(3L, "Cat #3", "https://i.imgur.com/p19dizd.png"),
                ImageData(4L, "Cat #4", "https://i.imgur.com/ZgmBLGu.png"),
                ImageData(5L, "Cat #5", "https://i.imgur.com/cbC9z3q.png"),
                ImageData(6L, "Cat #6", "https://i.imgur.com/tsL4ZXm.png"),
                ImageData(7L, "Cat #7", "https://i.imgur.com/6PBHCta.png"),
                ImageData(8L, "Cat #8", "https://i.imgur.com/wTBgyLu.png")
            )
        )
        val list = listOf(
            HeaderRow("F A K E   N E W S"),
            HorizontalListRow(horizontalList),
            NormalRow(
                NormalAutoData(
                    autoIncrementingId.next(),
                    "1. Africa declared free of wild polio",
                    "728 points by riffraff 8 hours ago | hide | 125 comments"
                )
            ),
            NormalRow(
                NormalAutoData(
                    autoIncrementingId.next(),
                    "2. Gamers Will Love This: Sometimes It’s Just Fun To Relax With A Nice Jigsaw Puzzle",
                    "841 points by rafaro 4 hours ago | hide | 85 comments"
                )
            ),
            NormalRow(
                NormalAutoData(
                    autoIncrementingId.next(),
                    "3. Trouble Not Your Mind, Gamers: Video Games Are Considered Cool Right Now",
                    "46 points by rhasta 2 hours ago | hide | 15 comments"
                )
            ),
            NormalRow(
                NormalAutoData(
                    autoIncrementingId.next(),
                    "4. ExxonMobil To Simplify Oil Extraction By Cutting Earth In Half",
                    "460 points by shaman 13 hours ago | hide | 151 comments"
                )
            ),
            FooterRow(
                FooterRow.Data(autoIncrementingId.next(), "© 2020 Fake News, Inc.")
            )
        )
        val state = State(list)
        adapter.submitList(list)

        state.setObserver {
            adapter.submitList(it)
        }

        findViewById<View>(R.id.shuffleButton).setOnClickListener {
            state.shuffle()
        }

        findViewById<View>(R.id.removeButton).setOnClickListener {
            state.removeRow()
        }

        findViewById<View>(R.id.addButton).setOnClickListener {
            counter++
            state.addRow(
                NormalRow(
                    NormalAutoData(
                        counter.toLong(),
                        "Title #$counter",
                        "Description #$counter"
                    )
                )
            )
        }
    }

    class State(private var list: List<ListItem<AutoData>> = listOf()) {
        var callback: ((List<ListItem<AutoData>>) -> Unit)? = null
        fun setObserver(_callback: (List<ListItem<AutoData>>) -> Unit) {
            callback = _callback
        }

        fun shuffle() {
            val newList = list.shuffled()
            callback?.invoke(newList)
        }

        fun addRow(newRow: ListItem<AutoData>) {
            val tempList = mutableListOf<ListItem<AutoData>>()
            tempList.addAll(list)
            tempList.add(tempList.size - 1, newRow)
            list = tempList
            callback?.invoke(list)
        }

        fun removeRow() {
            val tempList = mutableListOf<ListItem<AutoData>>()
            tempList.addAll(list)
            tempList.removeAt(tempList.size - 2)
            list = tempList
            callback?.invoke(list)
        }
    }
}
