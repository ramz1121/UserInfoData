package com.example.userInfodata.di.module

import androidx.lifecycle.LifecycleRegistry
import com.example.userInfodata.di.ViewModelScope
import com.example.userInfodata.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)


}