package com.example.userInfodata.ui.fullimage

import com.example.userInfodata.data.repository.ApiHelperImpl
import com.example.userInfodata.data.repository.UserRepository
import com.example.userInfodata.ui.base.BaseViewModel
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class FullImageViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper, private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {

    }
}