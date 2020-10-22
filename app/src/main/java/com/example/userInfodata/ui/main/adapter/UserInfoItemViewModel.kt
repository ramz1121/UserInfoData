package com.example.userInfodata.ui.main.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.ui.base.BaseItemViewModel
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserInfoItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<UserInfo>(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {

    }
    val email: LiveData<String> = Transformations.map(data) { it.email }
    val id: LiveData<Int> = Transformations.map(data) { it.id }
    val name: LiveData<String> = Transformations.map(data) { it.name }
    val phone: LiveData<String> = Transformations.map(data) { it.phone }

}
