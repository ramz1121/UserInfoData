package com.example.userInfodata.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.data.repository.ApiHelperImpl
import com.example.userInfodata.data.repository.UserRepository
import com.example.userInfodata.ui.base.BaseViewModel
import com.example.userInfodata.utils.common.Resource
import com.example.userInfodata.utils.espresso.EspressoIdlingResource
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper, private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {}
    val userList= MutableLiveData<Resource<List<UserInfo>>>()
    init {
        getUserList()
    }

    fun getUserList() {

        if (checkInternetConnectionWithMessage()) {
            EspressoIdlingResource.increment()
            userList.postValue(Resource.loading(null))
            compositeDisposable.addAll(
               userRepository.getUserList()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            userList.postValue(Resource.success(it))
                        }, {
                            handleNetworkError(it)
                            userList.postValue(Resource.error(it.toString(), null))
                        }
                    )

            )
            EspressoIdlingResource.decrement()
        }
    }
    fun getUsers(): LiveData<Resource<List<UserInfo>>> {
        return userList
    }
}