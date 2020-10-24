package com.example.userInfodata.di.component

import com.example.userInfodata.di.component.ApplicationComponent
import com.example.userInfodata.di.module.ApplicationTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent : ApplicationComponent {
}