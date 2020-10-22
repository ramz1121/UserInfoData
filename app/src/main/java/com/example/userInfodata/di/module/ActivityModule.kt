package com.example.userInfodata.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userInfodata.data.repository.UserRepository
import com.example.userInfodata.ui.album.AlbumViewModel
import com.example.userInfodata.ui.album.adapter.AlbumItemViewHolder
import com.example.userInfodata.ui.album.adapter.AlbumsListAdapter
import com.example.userInfodata.ui.base.BaseActivity
import com.example.userInfodata.ui.fullimage.FullImageViewModel
import com.example.userInfodata.ui.main.MainViewModel
import com.example.userInfodata.ui.main.adapter.UserListAdapter
import com.example.userInfodata.utils.ViewModelProviderFactory
import com.example.userInfodata.utils.network.NetworkHelper
import com.example.userInfodata.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
            //this lambda creates and return SplashViewModel
        }).get(MainViewModel::class.java)

    @Provides
    fun provideUserListAdapter() = UserListAdapter(activity.lifecycle, ArrayList())

    @Provides
    fun provideAlbumViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): AlbumViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(AlbumViewModel::class) {
            AlbumViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
            //this lambda creates and return SplashViewModel
        }).get(AlbumViewModel::class.java)
    @Provides
    fun provideFullImageViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): FullImageViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(FullImageViewModel::class) {
            FullImageViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
            //this lambda creates and return SplashViewModel
        }).get(FullImageViewModel::class.java)

    @Provides
    fun provideAlbumsListAdapter() = AlbumsListAdapter(activity.lifecycle, ArrayList())

}