package com.worker8.auto.adapter.library

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass


class AutoAdapter : ListAdapter<BaseRow<out AutoData>, RecyclerView.ViewHolder>(Comparator) {

    private val map = mutableMapOf<KClass<*>, Int>()
    private val layoutMap = mutableMapOf<Int, Int>()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int) = getItem(position).data.id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(layoutMap[viewType] ?: -1, parent, false)
            .let {
                object : RecyclerView.ViewHolder(it) {}
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("ddw", "onBind[$position]: ${getItem(position)}:: $holder")
        getItem(position).bind(holder.itemView)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        if (map[item::class] == null) {
            map[item::class] = map.size
        }
        val type = map[item::class] ?: -1
        layoutMap[type] = item.layoutResId
        return type
    }
}

private object Comparator : DiffUtil.ItemCallback<BaseRow<out AutoData>>() {
    override fun areItemsTheSame(
        oldItem: BaseRow<out AutoData>,
        newItem: BaseRow<out AutoData>
    ) = oldItem.data.id == newItem.data.id

    override fun areContentsTheSame(
        oldItem: BaseRow<out AutoData>,
        newItem: BaseRow<out AutoData>
    ) = oldItem.data.isContentSame(newItem.data)
}