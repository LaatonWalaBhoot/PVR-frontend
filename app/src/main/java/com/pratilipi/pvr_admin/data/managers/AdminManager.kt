package com.pratilipi.pvr_admin.data.managers

import com.pratilipi.pvr_admin.data.models.User

class AdminManager {

    private lateinit var user: User
    private lateinit var authToken: String

    companion object {
        private val adminManager: AdminManager = AdminManager()


        @Synchronized
        fun getInstance(): AdminManager {
            return adminManager
        }
    }

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser(): User {
        return user
    }

    fun setToken(string: String) {
        authToken = string
    }

    fun getToken(): String {
        return authToken
    }
}