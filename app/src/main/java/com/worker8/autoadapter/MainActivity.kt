package com.worker8.autoadapter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.worker8.auto.adapter.library.AutoAdapter
import com.worker8.auto.adapter.library.BaseRow
import com.worker8.auto.adapter.library.AutoData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = AutoAdapter()
        recyclerView.adapter = adapter
        var counter = 5
        val list = listOf(
            HeaderRow("F A K E   N E W S"),
            NormalRow(
                NormalAutoData(
                    1,
                    "1. Africa declared free of wild polio",
                    "728 points by riffraff 8 hours ago | hide | 125 comments"
                )
            ),
            NormalRow(
                NormalAutoData(
                    2,
                    "2. Gamers Will Love This: Sometimes It’s Just Fun To Relax With A Nice Jigsaw Puzzle",
                    "841 points by rafaro 4 hours ago | hide | 85 comments"
                )
            ),
            NormalRow(
                NormalAutoData(
                    3,
                    "3. Trouble Not Your Mind, Gamers: Video Games Are Considered Cool Right Now",
                    "46 points by rhasta 2 hours ago | hide | 15 comments"
                )
            ),
            NormalRow(
                NormalAutoData(
                    4,
                    "4. ExxonMobil To Simplify Oil Extraction By Cutting Earth In Half",
                    "460 points by shaman 13 hours ago | hide | 151 comments"
                )
            ),
            FooterRow("© 2020 Fake News, Inc.")
        )
        val state = State(list)
        adapter.submitList(list)
        state.setObserver {
            adapter.submitList(it)
        }

        shuffleButton.setOnClickListener {
            state.shuffle()
        }

        removeButton.setOnClickListener {
            state.removeRow()
        }

        addButton.setOnClickListener {
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

    class State(var list: List<BaseRow<out AutoData>> = listOf()) {
        var callback: ((List<BaseRow<out AutoData>>) -> Unit)? = null
        fun setObserver(_callback: (List<BaseRow<out AutoData>>) -> Unit) {
            callback = _callback
        }

        fun shuffle() {
            val newList = list.shuffled()
            newList.forEachIndexed { index, item ->
                Log.d("ddw", "shuffled[$index] = $item: ${item.data}")
            }
            callback?.invoke(newList)
        }

        fun addRow(newRow: BaseRow<out AutoData>) {
            val tempList = mutableListOf<BaseRow<out AutoData>>()
            tempList.addAll(list)
            tempList.add(tempList.size - 1, newRow)
            list = tempList
            callback?.invoke(list)
        }

        fun removeRow() {
            val tempList = mutableListOf<BaseRow<out AutoData>>()
            tempList.addAll(list)
            tempList.removeAt(tempList.size - 2)
            list = tempList
            callback?.invoke(list)
        }
    }

}
