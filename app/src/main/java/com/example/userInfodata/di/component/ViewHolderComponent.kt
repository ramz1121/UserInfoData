package com.example.userInfodata.di.component

import com.example.userInfodata.di.ViewModelScope
import com.example.userInfodata.di.module.ViewHolderModule
import com.example.userInfodata.ui.album.adapter.AlbumItemViewHolder
import com.example.userInfodata.ui.main.adapter.UserInfoItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: UserInfoItemViewHolder)
    fun inject(viewHolder: AlbumItemViewHolder)

}