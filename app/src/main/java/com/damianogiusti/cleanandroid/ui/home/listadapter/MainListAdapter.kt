package com.damianogiusti.cleanandroid.ui.home.listadapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.damianogiusti.cleanandroid.R
import com.damianogiusti.cleanandroid.adapter.BaseRecyclerViewAdapter
import com.damianogiusti.cleanandroid.viewmodel.ProvinceViewModel

/**
 * Created by Damiano Giusti on 04/05/17.
 */
class MainListAdapter : BaseRecyclerViewAdapter<ProvinceViewModel, MainListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.province_list_item_layout, parent, false)
        return MainListViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MainListViewHolder, position: Int) {
        val provinceViewModel = dataset[position]

        viewHolder.titleTextView.text = provinceViewModel.name
        viewHolder.subtitleTextView.text = provinceViewModel.toString()
    }

    override fun getItemId(position: Int): Long = dataset[position].hashCode().toLong()
}

class MainListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.titleTextView) lateinit var titleTextView: TextView

    @BindView(R.id.subtitleTextView) lateinit var subtitleTextView: TextView

    init {
        ButterKnife.bind(this, itemView)
    }
}