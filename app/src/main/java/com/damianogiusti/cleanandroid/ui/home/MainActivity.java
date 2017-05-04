package com.damianogiusti.cleanandroid.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.damianogiusti.cleanandroid.BaseActivity;
import com.damianogiusti.cleanandroid.R;
import com.damianogiusti.cleanandroid.ui.home.listadapter.MainListAdapter;
import com.damianogiusti.cleanandroid.viewmodel.ProvinceViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Inject MainPresenter mainPresenter;

    private MainListAdapter mainListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind the annotated views
        bindViews(this);
        setupViews();

        // create the presenter
        mainPresenter.create(this, savedInstanceState);
    }

    private void setupViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onInject() {
        super.onInject();
        // inject the use case Dagger component
        // will be useful when we'll add some logic to the app
        getUseCaseComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.destroy();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showList(List<ProvinceViewModel> list) {
        if (mainListAdapter == null) {
            mainListAdapter = new MainListAdapter();
            recyclerView.setAdapter(mainListAdapter);
        }
        mainListAdapter.setDataset(list);
        mainListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(context(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context context() {
        return this;
    }
}
