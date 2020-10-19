package com.example.userInfodata.di.component

import com.example.userInfodata.di.module.ActivityModule
import com.example.userInfodata.ui.main.MainActivity
import com.mindorks.bootcamp.instagram.di.ActivityScope
import com.mindorks.bootcamp.instagram.di.ApplicationContext
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}