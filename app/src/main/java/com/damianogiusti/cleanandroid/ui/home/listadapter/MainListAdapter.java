package com.damianogiusti.cleanandroid.ui.home.listadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damianogiusti.cleanandroid.R;
import com.damianogiusti.cleanandroid.adapter.BaseRecyclerViewAdapter;
import com.damianogiusti.cleanandroid.viewmodel.ProvinceViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damiano Giusti on 04/05/17.
 */
public class MainListAdapter extends BaseRecyclerViewAdapter<ProvinceViewModel, MainListAdapter.MainListViewHolder> {

    public MainListAdapter() {

    }

    @Override
    public MainListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.province_list_item_layout, parent, false);
        MainListViewHolder viewHolder = new MainListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainListViewHolder viewHolder, int position) {
        ProvinceViewModel provinceViewModel = dataset.get(position);

        viewHolder.titleTextView.setText(provinceViewModel.getName());
        viewHolder.subtitleTextView.setText(provinceViewModel.toString());
    }

    @Override
    public long getItemId(int position) {
        return dataset.get(position).hashCode();
    }

    static final class MainListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.titleTextView) TextView titleTextView;
        @BindView(R.id.subtitleTextView) TextView subtitleTextView;

        public MainListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
