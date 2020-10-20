package com.example.userInfodata

import android.app.Application
import com.example.userInfodata.di.component.ApplicationComponent
import com.example.userInfodata.di.component.DaggerApplicationComponent
import com.example.userInfodata.di.module.ApplicationModule

class UserInfoApplication : Application() {



    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }

}