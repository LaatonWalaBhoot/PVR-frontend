package com.pratilipi.pvr_admin

interface MainNavigator {

    fun initLoginFragment()

    fun initSignUpFragment()

    fun initTasksFragment()

    fun initCreateUserFragment()

    fun initUserEmailFragment()

    fun initMovieEmailFragment()

    fun initEmailFragment(movie: String, email: String)
}