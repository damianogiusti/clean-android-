package com.damianogiusti.cleanandroid.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.damianogiusti.cleanandroid.BaseActivity;
import com.damianogiusti.cleanandroid.R;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.helloWorldTextView) TextView helloWorldTextView;

    @Inject MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind the annotated views
        bindViews(this);
        // create the presenter
        mainPresenter.create(this, savedInstanceState);
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
    public Context context() {
        return this;
    }
}
