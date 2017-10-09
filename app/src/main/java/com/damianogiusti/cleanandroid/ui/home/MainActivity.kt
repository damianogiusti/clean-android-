package com.damianogiusti.cleanandroid.ui.home

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import com.damianogiusti.cleanandroid.R
import com.damianogiusti.cleanandroid.mvp.MvpActivity
import com.damianogiusti.cleanandroid.mvp.Presenter
import com.damianogiusti.cleanandroid.ui.home.listadapter.MainListAdapter
import com.damianogiusti.cleanandroid.viewmodel.ProvinceViewModel
import javax.inject.Inject

class MainActivity : MvpActivity(), MainView {

    @BindView(R.id.progressBar) lateinit var progressBar: ProgressBar
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView

    @Inject lateinit var presenter: MainPresenter

    private val mainListAdapter: MainListAdapter by lazy {
        MainListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
    }

    override fun onInject() {
        super.onInject()
        mvpComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private methods
    ///////////////////////////////////////////////////////////////////////////

    private fun setupViews() {
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
    }

    ///////////////////////////////////////////////////////////////////////////
    // MainView
    ///////////////////////////////////////////////////////////////////////////

    override fun showMessage(message: String) {
        Toast.makeText(context(), message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun showList(list: List<ProvinceViewModel>) {
        recyclerView.adapter = mainListAdapter
        mainListAdapter.dataset = list.toMutableList()
        mainListAdapter.notifyDataSetChanged()
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context(), errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun context(): Context = this

    ///////////////////////////////////////////////////////////////////////////
    // PresenterProvider
    ///////////////////////////////////////////////////////////////////////////

    override fun getPresenter(): Presenter<*> = presenter
}
