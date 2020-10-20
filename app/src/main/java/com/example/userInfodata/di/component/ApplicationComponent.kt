package com.example.userInfodata.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.userInfodata.UserInfoApplication
import com.example.userInfodata.data.remote.NetworkService
import com.example.userInfodata.di.ApplicationContext
import com.example.userInfodata.di.module.ApplicationModule
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent{
    fun inject(app: UserInfoApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService



    fun getNetworkHelper(): NetworkHelper

    /**---------------------------------------------------------------------------
     * Dagger will internally create UserRepository instance using constructor injection.
     * Dependency through constructor
     * UserRepository ->
     *  [NetworkService -> Nothing is required],
     *  [DatabaseService -> Nothing is required],
     *  [UserPreferences -> [SharedPreferences -> provided by the function provideSharedPreferences in ApplicationModule class]]
     * So, Dagger will be able to create an instance of UserRepository by its own using constructor injection
     *---------------------------------------------------------------------------------
     */

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable
}