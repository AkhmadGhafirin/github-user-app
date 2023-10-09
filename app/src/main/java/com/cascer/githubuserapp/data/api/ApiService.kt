package com.cascer.githubuserapp.data.api

import com.cascer.githubuserapp.data.model.GithubResponse
import com.cascer.githubuserapp.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun searchUser(
        @Query("q") search: String
    ): Response<GithubResponse>

    @GET("users/{username}")
    suspend fun userDetail(
        @Path("username") username: String
    ): Response<UserResponse>

    @GET("users/{username}/followers")
    suspend fun userFollowers(
        @Path("username") username: String
    ): Response<List<UserResponse>>

    @GET("users/{username}/following")
    suspend fun userFollowing(
        @Path("username") username: String
    ): Response<List<UserResponse>>
}