package com.pratilipi.pvr_admin.data.remote

import android.util.Log
import com.pratilipi.pvr_admin.data.managers.AdminManager
import com.pratilipi.pvr_admin.data.models.City
import com.pratilipi.pvr_admin.data.models.EmailRequest
import com.pratilipi.pvr_admin.data.models.Movie
import com.pratilipi.pvr_admin.data.models.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson



class ApiInstance() {

    companion object {
        private val apiInstance: ApiInstance = ApiInstance()
        private lateinit var apiService: ApiService
        private lateinit var okHttpClient: OkHttpClient

        @Synchronized
        fun getInstance(): ApiInstance {
            return apiInstance
        }
    }

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        apiService = Retrofit.Builder()
            .baseUrl("https://pvr-admin.herokuapp.com/admin/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build().create(ApiService::class.java)
    }

    fun getAllCities(onGetCitiesListener: OnGetCitiesListener) {
        apiService.getAllCities().enqueue(object : Callback<List<City>> {
            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                onGetCitiesListener.onGetCitiesFail(t.message!!)
            }

            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                if(response.code() == 200) {
                    onGetCitiesListener.onGetCitiesSuccess(response.body()!!)
                } else {
                    onGetCitiesListener.onGetCitiesFail("cannot get cities")
                }
            }
        })

    }

    fun getAllMovies(onGetMoviesListener: OnGetMoviesListener) {
        apiService.getAllMovies().enqueue(object : Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                onGetMoviesListener.onGetMoviesFail(t.message!!)
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if(response.code() == 200) {
                    onGetMoviesListener.onGetMoviesSuccess(response.body()!!)
                } else {
                    onGetMoviesListener.onGetMoviesFail("Cannot get movies")
                }
            }
        })

    }

    fun getUser(name:String, onGetUserListener: OnGetUserListener) {
        apiService.getUser(AdminManager.getInstance().getToken(), name).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                onGetUserListener.onGetUserFail(t.message!!)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.code() == 200) {
                    onGetUserListener.onGetUserSuccess(response.body()!!)
                } else {
                    onGetUserListener.onGetUserFail("User not found")
                }
            }
        })
    }

    fun signUp(name: String, email: String, password: String, city: String, onSignUpListener: OnSignUpListener) {
        apiService.signUp(name, email, password, city).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                onSignUpListener.onSignUpFail(t.message!!)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.code() == 200) {
                    AdminManager.getInstance().setToken(response.headers().get("x-auth-token").toString())
                    onSignUpListener.onSignUpSuccess(response.body()!!)
                } else {
                    onSignUpListener.onSignUpFail("one or more sign up fields are invalid")
                }
            }
        })
    }


    fun login(name: String, password: String, onLoginListener: OnLoginListener) {
        apiService.login(name, password).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                onLoginListener.onLoginFail(t.message!!)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.code() == 200) {
                    AdminManager.getInstance().setToken(response.headers().get("x-auth-token").toString())
                    onLoginListener.onLoginSuccess(response.body()!!)
                } else {
                    onLoginListener.onLoginFail("username or password is wrong")
                }
            }
        })
    }

    fun createUser(name: String, email: String, password: String, city: String, onCreateUserListener: OnCreateUserListener) {
        apiService.createUser(AdminManager.getInstance().getToken(), name, email, password, city).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                onCreateUserListener.onCreateUserFail(t.message!!)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.code() == 200) {
                    onCreateUserListener.onCreateUserSuccess(response.body()!!)
                } else {
                    onCreateUserListener.onCreateUserFail("one or more user fields are invalid")
                }
            }
        })
    }

    fun sendEmailForUser(emailRequest: EmailRequest, onSendEmailUserListener: OnSendEmailUserListener) {
        apiService.sendEmailForUser(AdminManager.getInstance().getToken(), emailRequest).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                onSendEmailUserListener.onSendEmailUserFail(t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.code() == 200) {
                    onSendEmailUserListener.onSendEmailUserSuccess(response.body()!!)
                } else {
                    onSendEmailUserListener.onSendEmailUserFail("could not send mail")
                }
            }
        })
    }

    fun sendEmailForMovie(emailRequest: EmailRequest, onSendEmailMovieListener: OnSendEmailMovieListener) {
        apiService.sendEmailForMovie(AdminManager.getInstance().getToken(), emailRequest).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                onSendEmailMovieListener.onSendEmailMovieFail(t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.code() == 200) {
                    onSendEmailMovieListener.onSendEmailMovieSuccess(response.body()!!)
                } else {
                    onSendEmailMovieListener.onSendEmailMovieFail("could not send email")
                }
            }
        })
    }

    //Listeners
    interface OnGetCitiesListener {
        fun onGetCitiesSuccess(list: List<City>)

        fun onGetCitiesFail(string: String)
    }

    interface OnGetMoviesListener {
        fun onGetMoviesSuccess(list: List<Movie>)

        fun onGetMoviesFail(string: String)
    }

    interface OnGetUserListener {
        fun onGetUserSuccess(user: User)

        fun onGetUserFail(string: String)
    }

    interface OnSignUpListener {
        fun onSignUpSuccess(user: User)

        fun onSignUpFail(string: String)
    }

    interface OnLoginListener {
        fun onLoginSuccess(user: User)

        fun onLoginFail(string: String)
    }

    interface OnCreateUserListener {
        fun onCreateUserSuccess(user: User)

        fun onCreateUserFail(string: String)
    }

    interface OnSendEmailUserListener {
        fun onSendEmailUserSuccess(string: String)

        fun onSendEmailUserFail(string: String)
    }

    interface OnSendEmailMovieListener {
        fun onSendEmailMovieSuccess(string: String)

        fun onSendEmailMovieFail(string: String)
    }
}