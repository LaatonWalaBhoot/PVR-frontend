package com.pratilipi.pvr_admin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.pratilipi.pvr_admin.ui.main.createUser.CreateUserFragment
import com.pratilipi.pvr_admin.ui.main.email.EmailFragment
import com.pratilipi.pvr_admin.ui.main.email.movie.MovieEmailFragment
import com.pratilipi.pvr_admin.ui.main.email.user.UserEmailFragment
import com.pratilipi.pvr_admin.ui.main.login.LoginFragment
import com.pratilipi.pvr_admin.ui.main.signUp.SignUpFragment
import com.pratilipi.pvr_admin.ui.main.tasks.TasksFragment

class MainActivity : AppCompatActivity(), MainNavigator {

    override fun initEmailFragment(movie: String, email: String) {
        val bundle = Bundle()
        bundle.putString("movie", movie)
        bundle.putString("user", email)
        val emailFragment = EmailFragment.newInstance()
        emailFragment.arguments = bundle
        addFragment(emailFragment)
        setTitle("Email")
    }

    override fun initMovieEmailFragment() {
        addFragment(MovieEmailFragment.newInstance())
        setTitle("Movie Email")
    }

    override fun initUserEmailFragment() {
        addFragment(UserEmailFragment.newInstance())
        setTitle("User Email")
    }

    override fun initCreateUserFragment() {
        addFragment(CreateUserFragment.newInstance())
        setTitle("Create User")
    }

    override fun initTasksFragment() {
        initFragment(TasksFragment.newInstance())
        setTitle("Welcome Admin")
    }

    override fun initLoginFragment() {
        initFragment(LoginFragment.newInstance())
        setTitle("Login")
    }

    override fun initSignUpFragment() {
        initFragment(SignUpFragment.newInstance())
        setTitle("Sign Up")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            initLoginFragment()
        }
    }

    private fun initFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

    private fun setTitle(title: String) {
        if(supportActionBar!=null) {
            supportActionBar!!.title = title
        }
    }

}
