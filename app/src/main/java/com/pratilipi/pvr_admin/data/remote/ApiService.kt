package com.pratilipi.pvr_admin.data.remote

import com.pratilipi.pvr_admin.data.managers.AdminManager
import com.pratilipi.pvr_admin.data.models.City
import com.pratilipi.pvr_admin.data.models.EmailRequest
import com.pratilipi.pvr_admin.data.models.Movie
import com.pratilipi.pvr_admin.data.models.User
import retrofit2.Call
import retrofit2.http.*


public interface ApiService {

    @GET("getAllCities")
    fun getAllCities(): Call<List<City>>

    @GET("getAllMovies")
    fun getAllMovies(): Call<List<Movie>>

    @GET("getUser")
    fun getUser(@Header("x-auth-token") header: String, @Query("name") name: String): Call<User>

    @POST("login")
    fun login(@Query("name") name:String, @Query("password") password: String): Call<User>

    @POST("signUp")
    fun signUp(@Query("name") name:String, @Query("email") email:String, @Query("password") password:String, @Query("city") city:String): Call<User>

    @POST("createUser")
    fun createUser(@Header("x-auth-token") header: String, @Query("name") name:String, @Query("email") email:String, @Query("password") password:String, @Query("city") city:String): Call<User>

    @POST("sendEmailForUser")
    fun sendEmailForUser(@Header("x-auth-token") header: String, @Body emailRequest: EmailRequest): Call<String>

    @POST("sendEmailForMovie")
    fun sendEmailForMovie(@Header("x-auth-token") header: String, @Body emailRequest: EmailRequest): Call<String>

}