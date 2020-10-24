package com.example.userInfodata.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.corona.java.userinfo.model.UserAlbums
import com.example.userInfodata.data.repository.ApiHelperImpl
import com.example.userInfodata.data.repository.UserRepository
import com.example.userInfodata.ui.base.BaseViewModel
import com.example.userInfodata.utils.common.Resource
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import java.lang.Exception

class AlbumViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper, private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    val userAlbums = MutableLiveData<Resource<List<UserAlbums>>>()
    init {
        getUserAlbums()
    }
    override fun onCreate() {}
/********************************Coroutines******************************************/
    fun getUserAlbums() {
        if (checkInternetConnectionWithMessage()) {
            viewModelScope.launch {
                userAlbums.postValue(Resource.loading(null))
                try {
                    val albums = userRepository.getUserAlbum()
                    userAlbums.postValue(Resource.success(albums))
                } catch (e: Exception) {
                    handleNetworkError(e)
                    userAlbums.postValue(Resource.error(e.toString(), null))
                }
            }
        }
    }
    fun getAlbum(): LiveData<Resource<List<UserAlbums>>> {
        return userAlbums
    }
}