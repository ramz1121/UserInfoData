package com.example.userInfodata.di.module

import android.app.Application
import android.content.Context
import com.example.userInfodata.BuildConfig
import com.example.userInfodata.UserInfoApplication
import com.example.userInfodata.data.remote.NetworkService
import com.example.userInfodata.data.remote.Networking
import com.example.userInfodata.di.ApplicationContext
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.RxSchedulerProvider
import com.example.userInfodata.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationTestModule(private val application:UserInfoApplication ) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()


    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}