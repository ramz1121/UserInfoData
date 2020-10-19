package com.example.userInfodata

import android.app.Application

class UserInfoApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {

    }

   
}