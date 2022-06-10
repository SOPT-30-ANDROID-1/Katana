package com.example.seminar1.util

import android.content.Context
import android.content.SharedPreferences

object SOPTSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private lateinit var preferences: SharedPreferences

    fun init(context: Context): SharedPreferences {
        return context.getSharedPreferences(STORAGE_KEY,Context.MODE_PRIVATE)
    }

    fun getAutoLogin(context: Context): Boolean {
        return init(context).getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, value: Boolean){
        init(context).edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun setLogout(context: Context){
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .clear()
            .apply()
    }
}