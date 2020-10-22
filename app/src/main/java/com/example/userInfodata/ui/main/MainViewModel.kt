package com.example.userInfodata.ui.main

import androidx.lifecycle.MutableLiveData
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.data.repository.UserRepository
import com.example.userInfodata.ui.base.BaseViewModel
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper, private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {}
    val userList: MutableLiveData<List<UserInfo>> = MutableLiveData()

    fun getUserList() {
        if (checkInternetConnection()) {
            compositeDisposable.addAll(
                userRepository.getUserList()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            userList.postValue(it)
                        }, {
                            handleNetworkError(it)
                        }
                    )

            )
        }
    }
}