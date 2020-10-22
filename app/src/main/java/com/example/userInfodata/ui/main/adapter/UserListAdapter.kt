package com.example.userInfodata.ui.main.adapter

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.ui.base.BaseAdapter

class UserListAdapter(
    parentLifecycle: Lifecycle,
    private val userInfo: ArrayList<UserInfo>
    ) : BaseAdapter<UserInfo, UserInfoItemViewHolder>(parentLifecycle, userInfo) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserInfoItemViewHolder(parent)

    fun clear() {
        this.userInfo.clear()
        notifyDataSetChanged()
    }
}