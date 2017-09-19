package com.damianogiusti.cleanandroid.ui.home;

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
import com.damianogiusti.cleanandroid.uimodel.ProvinceUiModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Inject MainViewModel mainViewModel;

    private MainListAdapter mainListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind the annotated views
        bindViews(this);
        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainViewModel.provinces()
                .doOnSubscribe(compositeDisposable::add)
                .subscribe(this::showList, error -> this.showError(error.getLocalizedMessage()));

        mainViewModel.isLoading()
                .doOnSubscribe(compositeDisposable::add)
                .subscribe(isLoading -> {
                    if (isLoading) {
                        showLoading();
                    } else {
                        hideLoading();
                    }
                }, error -> showError(error.getLocalizedMessage()));
    }

    @Override
    protected void onInject() {
        super.onInject();
        // inject the use case Dagger component
        // will be useful when we'll add some logic to the app
        getViewModelsComponent().inject(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private methods
    ///////////////////////////////////////////////////////////////////////////

    private void setupViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void showList(List<ProvinceUiModel> list) {
        if (mainListAdapter == null) {
            mainListAdapter = new MainListAdapter();
            recyclerView.setAdapter(mainListAdapter);
        }
        mainListAdapter.setDataset(list);
        mainListAdapter.notifyDataSetChanged();
    }

    private void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}