package com.example.seminar1

data class ResponseWrapper<T>(
        val status: Int,
        val message: String,
        val data: T? = null
)
