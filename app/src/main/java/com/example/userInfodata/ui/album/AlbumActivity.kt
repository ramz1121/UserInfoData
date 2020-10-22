package com.example.userInfodata.ui.album

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.corona.java.userinfo.model.UserAlbums
import com.example.userInfodata.R
import com.example.userInfodata.di.component.ActivityComponent
import com.example.userInfodata.ui.album.adapter.AlbumsListAdapter
import com.example.userInfodata.ui.base.BaseActivity
import com.example.userInfodata.ui.main.adapter.UserListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class AlbumActivity : BaseActivity<AlbumViewModel>() {
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var albumListAdapter: AlbumsListAdapter

    private var userId=0

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }


    override fun setupView(savedInstanceState: Bundle?) {
        userId = intent.getIntExtra("id",0)
        progressBar.visibility = View.VISIBLE
        mainRecycleview.layoutManager = linearLayoutManager
        mainRecycleview.addItemDecoration(
            DividerItemDecoration(
                mainRecycleview.context,
                (mainRecycleview.layoutManager as LinearLayoutManager).orientation
            )
        )
        mainRecycleview.adapter = albumListAdapter
        albumListAdapter.clear()
        viewModel.getUserAlbums()
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.userAlbums.observe(this, Observer {
            it?.run { retrieveList(it) }
            progressBar.visibility = View.GONE
        })


    }

    // Function updates the users to the adapter list
    private fun retrieveList(users: List<UserAlbums>) {
        albumListAdapter.apply {
            val finalList = users.filter { (it.albumId == userId) }
            albumListAdapter.appendData(finalList)
            notifyDataSetChanged()

            //to change title of activity
            toolbar_title.text = "Album ID:" + (finalList[0].albumId).toString()
        }
    }
}