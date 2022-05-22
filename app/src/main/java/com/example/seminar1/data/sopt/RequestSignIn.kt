package com.example.seminar1.data.sopt

import com.google.gson.annotations.SerializedName

data class RequestSignIn(
    @SerializedName("email")
    val id : String,
    val password : String
)
