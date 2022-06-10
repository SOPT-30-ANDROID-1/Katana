package com.example.seminar1.data.sopt

import com.google.gson.annotations.SerializedName

data class RequestSignUp(
    @SerializedName("email")
    val id : String,
    val name : String,
    val password : String
)
