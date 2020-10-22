package com.example.userInfodata.ui.album.adapter

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.corona.java.userinfo.model.UserAlbums
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.ui.album.AlbumActivity
import com.example.userInfodata.ui.base.BaseItemViewModel
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AlbumItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<UserAlbums>(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {

    }
    val albumId: LiveData<Int> = Transformations.map(data) { it.albumId }
    val id: LiveData<Int> = Transformations.map(data) { it.id }
    val thumbnailUrl: LiveData<String> = Transformations.map(data) { it.thumbnailUrl }
    val title: LiveData<String> = Transformations.map(data) { it.title }
    val url: LiveData<String> = Transformations.map(data) { it.url }

    fun onItemClick(position: Int) {

    }

}
