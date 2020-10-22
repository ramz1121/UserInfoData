package com.example.userInfodata.ui.album

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userInfodata.R
import com.example.userInfodata.di.component.ActivityComponent
import com.example.userInfodata.ui.base.BaseActivity
import com.example.userInfodata.ui.main.adapter.UserListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class AlbumActivity: BaseActivity<AlbumViewModel>() {
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var userListAdapter: UserListAdapter

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        progressBar.visibility= View.VISIBLE
        mainRecycleview.layoutManager = linearLayoutManager
        mainRecycleview.addItemDecoration(
            DividerItemDecoration(
                mainRecycleview.context,
                (mainRecycleview.layoutManager as LinearLayoutManager).orientation
            )
        )
        mainRecycleview.adapter = userListAdapter
        toolbar_title.setText(R.string.app_name)
        userListAdapter.clear()

    }

    override fun setupObservers() {
        super.setupObservers()


    }
}