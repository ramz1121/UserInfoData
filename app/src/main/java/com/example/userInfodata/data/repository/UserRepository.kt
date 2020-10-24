package com.example.userInfodata.data.repository

import com.corona.java.userinfo.model.UserAlbums
import com.corona.java.userinfo.model.UserInfo
import io.reactivex.Single

interface UserRepository {
    fun getUserList(): Single<List<UserInfo>>

    suspend fun getUserAlbum(): List<UserAlbums>

}
