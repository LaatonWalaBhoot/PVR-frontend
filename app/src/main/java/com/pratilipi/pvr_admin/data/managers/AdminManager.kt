package com.pratilipi.pvr_admin.data.managers

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.pratilipi.pvr_admin.data.models.User

class AdminManager {

    private lateinit var user: User
    private lateinit var authToken: String
    private var gson: Gson = Gson()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    companion object {
        private val adminManager: AdminManager = AdminManager()


        @Synchronized
        fun getInstance(): AdminManager {
            return adminManager
        }
    }

    fun initPreferences(context: Context) {
        sharedPreferences = context.applicationContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

    fun setUser(user: User) {
        this.user = user
        editor = sharedPreferences.edit()
        val userString = gson.toJson(user)
        editor.putString("user", userString).apply()
    }

    fun getUser(): User? {
        val userString = sharedPreferences.getString("user", "")
        return if(TextUtils.isEmpty(userString)) {
            null
        } else {
            gson.fromJson(userString, User::class.java)
        }
    }

    fun setToken(string: String) {
        authToken = string
        editor = sharedPreferences.edit()
        editor.putString("token", authToken).apply()
    }

    fun getToken(): String {
        return sharedPreferences.getString("token", "")!!
    }
}