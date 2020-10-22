package com.example.userInfodata.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.R
import com.example.userInfodata.di.component.ViewHolderComponent
import com.example.userInfodata.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.list_item_view.view.*

class UserInfoItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<UserInfo, UserInfoItemViewModel>(R.layout.list_item_view, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)

    }

    override fun setupView(view: View) {
        view.setOnClickListener {


        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.email.observe(this, Observer {
            itemView.itemEmail.text = it
        })
        viewModel.name.observe(this, Observer {
            itemView.itemName.text = it
        })
        viewModel.phone.observe(this, Observer {
            itemView.itemPhoneNumber.text = it
        })
        viewModel.id.observe(this, Observer {
            itemView.itemId.text = "ID: "+it.toString()
        })
    }
}