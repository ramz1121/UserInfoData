package com.example.userInfodata.ui.album.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import com.corona.java.userinfo.model.UserAlbums
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.R
import com.example.userInfodata.di.component.ViewHolderComponent
import com.example.userInfodata.ui.album.AlbumActivity
import com.example.userInfodata.ui.base.BaseItemViewHolder
import com.example.userInfodata.utils.display.PicassoSSL
import kotlinx.android.synthetic.main.list_item_albums.view.*
import kotlinx.android.synthetic.main.list_item_view.view.*

class AlbumItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<UserAlbums, AlbumItemViewModel>(R.layout.list_item_albums, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)

    }

    override fun setupView(view: View) {
        view.setOnClickListener {
            viewModel.onItemClick(adapterPosition)
            /* val intent =
                 Intent(it.context, AlbumActivity::class.java) //context we got from constructor
             intent.putExtra("id", itemView.itemId.text)
             it.context.startActivity(intent)*/
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.title.observe(this, Observer {
            itemView.itemTitle.text = it
        })
        viewModel.thumbnailUrl.observe(this, Observer {
            PicassoSSL.getInstance(itemView.context)?.load(it)
                ?.placeholder(null)
                ?.into(itemView.itemIV)
        })


    }
}