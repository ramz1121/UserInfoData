package com.example.userInfodata.ui.main

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userInfodata.R
import com.example.userInfodata.di.component.ActivityComponent
import com.example.userInfodata.ui.base.BaseActivity
import com.example.userInfodata.ui.main.adapter.UserListAdapter
import com.example.userInfodata.utils.common.Status
import com.example.userInfodata.utils.espresso.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var userListAdapter: UserListAdapter

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        progressBar.visibility = View.VISIBLE
        EspressoIdlingResource.increment()
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
        viewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE

                    it.data?.let { users -> userListAdapter.appendData(users) }

                    mainRecycleview.visibility = View.VISIBLE
                    EspressoIdlingResource.decrement()
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    mainRecycleview.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}