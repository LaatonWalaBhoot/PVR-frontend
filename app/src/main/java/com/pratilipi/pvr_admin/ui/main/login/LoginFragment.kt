package com.pratilipi.pvr_admin.ui.main.login

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pratilipi.pvr_admin.MainNavigator
import com.pratilipi.pvr_admin.R
import com.pratilipi.pvr_admin.data.managers.AdminManager
import com.pratilipi.pvr_admin.data.models.User
import com.pratilipi.pvr_admin.data.remote.ApiInstance
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment(), ApiInstance.OnLoginListener {


    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var mainNavigator: MainNavigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainNavigator = context as MainNavigator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUp.setOnClickListener {
            mainNavigator.initSignUpFragment()
        }
        submit.setOnClickListener {
            submit.visibility = View.INVISIBLE
            signUp.visibility = View.INVISIBLE
            progress_layout.visibility = View.VISIBLE
            checkCredentialsAndLogin()
        }
    }

    private fun checkCredentialsAndLogin() {
        if(!TextUtils.isEmpty(username.text) && !TextUtils.isEmpty(password.text)) {
            ApiInstance.getInstance().login(username.text.toString(), password.text.toString(), this)
        }
    }

    override fun onLoginSuccess(user: User) {
        AdminManager.getInstance().setUser(user)
        Toasty.success(context!!, "Login Success").show()
        mainNavigator.initTasksFragment()
    }

    override fun onLoginFail(string: String) {
        submit.visibility = View.VISIBLE
        signUp.visibility = View.VISIBLE
        progress_layout.visibility = View.GONE
        Toasty.error(context!!, string).show()
    }

}
