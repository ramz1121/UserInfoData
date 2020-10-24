package com.example.userInfodata.data.repository

import com.corona.java.userinfo.model.UserAlbums
import com.corona.java.userinfo.model.UserInfo
import com.example.userInfodata.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHelperImpl @Inject constructor(
    private val networkService: NetworkService
) : UserRepository {
    override fun getUserList() = networkService.getUserList()

    override suspend fun getUserAlbum() = networkService.getAlbums()

}