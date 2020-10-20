package com.example.userInfodata.di.component

import com.example.userInfodata.di.ViewModelScope
import com.example.userInfodata.di.module.ViewHolderModule
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {


}