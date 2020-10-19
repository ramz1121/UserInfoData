package com.example.userInfodata.di.component

import android.app.Application
import android.content.Context
import com.example.userInfodata.UserInfoApplication
import com.mindorks.bootcamp.instagram.di.ApplicationContext
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationComponent::class])
interface ApplicationComponent{
    fun inject(app: UserInfoApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context
}