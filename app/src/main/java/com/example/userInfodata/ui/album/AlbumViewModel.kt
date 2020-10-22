package com.example.userInfodata.ui.album

import androidx.lifecycle.MutableLiveData
import com.corona.java.userinfo.model.UserAlbums
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.data.repository.UserRepository
import com.example.userInfodata.ui.base.BaseViewModel
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class AlbumViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper, private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val userAlbums: MutableLiveData<List<UserAlbums>> = MutableLiveData()
    override fun onCreate() {}

    fun getUserAlbums() {
        if (checkInternetConnection()) {
            compositeDisposable.addAll(
                userRepository.getUserAlbum()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            userAlbums.postValue(it)
                        }, {
                            handleNetworkError(it)
                        }
                    )

            )
        }
    }
}