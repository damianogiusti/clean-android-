package com.damianogiusti.cleanandroid.adapter

import android.support.v7.widget.RecyclerView
import java.util.*

/**
 * Created by Damiano Giusti on 04/05/17.
 */
abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    var dataset: MutableList<T> = ArrayList(0)

    fun add(item: T) {
        this.dataset.add(item)
    }

    fun addAll(elements: Collection<T>) {
        this.dataset.addAll(elements)
    }

    fun remove(position: Int) {
        this.dataset.removeAt(position)
    }

    fun remove(item: T) {
        this.dataset.remove(item)
    }

    fun clear() {
        this.dataset.clear()
    }

    override fun getItemCount(): Int = dataset.size
}
