package com.worker8.autoadapter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.NO_ID
import com.worker8.auto.adapter.library.AutoAdapter
import com.worker8.auto.adapter.library.BaseRow
import com.worker8.auto.adapter.library.AutoData
import com.worker8.autoadapter.data.ImageData
import com.worker8.autoadapter.data.NormalAutoData
import com.worker8.autoadapter.rows.*
import kotlinx.android.synthetic.main.activity_basic_list.*

class BasicListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_list)
        val adapter = AutoAdapter(hasStableIds = false)
        recyclerView.adapter = adapter

        var counter = 5
        val horizontalList = ColumnList(
            NO_ID, listOf(
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
//            HeaderRow("F A K E   N E W S"),
            ImageRow(),
            HorizontalListRow(horizontalList),
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
        recyclerView.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            val view = recyclerView.getChildAt(0);
            Log.d("ddw", "view: $view");
            if (view != null && recyclerView.getChildAdapterPosition(view) == 0) {
                view.translationY = (-view.top / 2).toFloat();// or use view.animate().translateY();
            }
        }
        state.setObserver {
            Log.d("ddw", "here 1");
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
