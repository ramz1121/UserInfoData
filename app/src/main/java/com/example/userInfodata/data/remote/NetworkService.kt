package com.example.userInfodata.data.remote

import com.corona.java.userinfo.model.UserAlbums
import com.corona.java.userinfo.model.UserInfo
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET(Endpoints.Users)
    fun getUserList(): Single<List<UserInfo>>

    @GET(Endpoints.Photos)
    suspend fun getAlbums():List<UserAlbums>
}