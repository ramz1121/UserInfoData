package com.example.userInfodata.di.module

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userInfodata.ui.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule (private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

}