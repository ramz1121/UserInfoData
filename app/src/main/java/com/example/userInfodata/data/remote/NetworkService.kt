package com.example.userInfodata.data.remote

import com.corona.java.userinfo.model.UserInfo
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET(Endpoints.Users)
    fun doLoginCall(): Single<UserInfo>
}