package com.example.userInfodata.ui.album.adapter

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.corona.java.userinfo.model.UserAlbums
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.ui.base.BaseAdapter

class AlbumsListAdapter(
    parentLifecycle: Lifecycle,
    private val userAlbums: ArrayList<UserAlbums>
    ) : BaseAdapter<UserAlbums, AlbumItemViewHolder>(parentLifecycle, userAlbums) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumItemViewHolder(parent)

    fun clear() {
        this.userAlbums.clear()
        notifyDataSetChanged()
    }
}