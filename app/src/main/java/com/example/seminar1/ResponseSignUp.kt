package com.example.seminar1

data class ResponseSignUp(
    val status : Int,
    val message : String,
    val data : Data
){
    data class Data(
        val id : Int
    )
}
