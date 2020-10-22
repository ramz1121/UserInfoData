package com.example.userInfodata.data.repository

import com.corona.java.userinfo.model.UserAlbums
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val networkService: NetworkService
) {
    fun getUserList():Single<List<UserInfo>> = networkService.getUserList()

    fun getUserAlbum():Single<List<UserAlbums>> = networkService.getAlbums()

}