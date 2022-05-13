package com.example.seminar1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{login}/followers")
    fun getFollowers(
            @Path("login") login: String
    ): Call<List<ResponseFollower>>

    @GET("users/{login}")
    fun getUser(
            @Path("login") login: String
    ): Call<ResponseUser>
}