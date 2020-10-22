package com.example.userInfodata.di.component

import com.example.userInfodata.di.ActivityScope
import com.example.userInfodata.di.module.ActivityModule
import com.example.userInfodata.ui.album.AlbumActivity
import com.example.userInfodata.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(albumActivity: AlbumActivity)
}