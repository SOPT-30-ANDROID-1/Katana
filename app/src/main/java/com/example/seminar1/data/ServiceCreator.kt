package com.example.seminar1.data

import com.example.seminar1.data.sopt.SoptService
import com.example.seminar1.data.github.GithubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://13.124.62.236/"
    private const val BASE_URL_GITHUB = "https://api.github.com/"

    private val soptRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_GITHUB)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val soptService: SoptService = soptRetrofit.create(SoptService::class.java)
    val githubService: GithubService = githubRetrofit.create(GithubService::class.java)
}