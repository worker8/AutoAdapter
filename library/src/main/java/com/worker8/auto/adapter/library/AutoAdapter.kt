package com.worker8.auto.adapter.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_ID
import kotlin.reflect.KClass

open class AutoAdapter(private val hasStableIds: Boolean = false) :
    ListAdapter<ListItem<AutoData>, RecyclerView.ViewHolder>(Comparator) {
    // KClass -> ViewType
    private val viewTypeCache = mutableMapOf<KClass<*>, Int>()

    // viewType -> layout resource id
    private val viewTypeToLayoutResIdMap = mutableMapOf<Int, Int>()

    init {
        setHasStableIds(hasStableIds)
    }

    override fun getItemId(position: Int) =
        if (hasStableIds) {
            getItem(position).data.id
        } else {
            NO_ID
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(viewTypeToLayoutResIdMap[viewType] ?: -1, parent, false)
            .let { object : RecyclerView.ViewHolder(it) {} }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        getItem(position).bind(holder.itemView, position)

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return viewTypeCache.getOrPut(item::class) { viewTypeCache.size }.also {
            viewTypeToLayoutResIdMap[it] = item.layoutResId
        }
    }
}

private object Comparator : DiffUtil.ItemCallback<ListItem<AutoData>>() {
    override fun areItemsTheSame(
        oldItem: ListItem<AutoData>,
        newItem: ListItem<AutoData>
    ) = oldItem.data.id == newItem.data.id

    override fun areContentsTheSame(
        oldItem: ListItem<AutoData>,
        newItem: ListItem<AutoData>
    ) = oldItem.data.isContentSame(newItem.data)
}