package com.pratilipi.pvr_admin.ui.main.email.user

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.pratilipi.pvr_admin.MainNavigator
import com.pratilipi.pvr_admin.R
import com.pratilipi.pvr_admin.data.managers.AdminManager
import com.pratilipi.pvr_admin.data.models.User
import com.pratilipi.pvr_admin.data.remote.ApiInstance
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.user_email_fragment.*

class UserEmailFragment: Fragment(), ApiInstance.OnGetUserListener {

    companion object {
        fun newInstance() = UserEmailFragment()
    }

    private lateinit var mainNavigator: MainNavigator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.user_email_fragment, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainNavigator = context as MainNavigator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_btn.setOnClickListener {
            if(!TextUtils.isEmpty(search_text.text)) {
                progress_bar.visibility = View.VISIBLE
                ApiInstance.getInstance().getUser(search_text.text.toString(), this)
            }
        }
        search_text.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                progress_bar.visibility = View.VISIBLE
                ApiInstance.getInstance().getUser(search_text.text.toString(), this)
                true
            } else {
                false
            }
        }

    }

    private fun setUserDetails(name: String, email: String, isAdmin: Boolean) {
        name_text.text = name
        email_text.text = email
        if(isAdmin) {
            admin_text.text = "Admin"
        } else {
            admin_text.text = "Not an Admin"
        }
    }

    override fun onGetUserSuccess(user: User) {
        progress_bar.visibility = View.GONE
        sendEmailToUser.visibility = View.VISIBLE
        setUserDetails(user.name!!, user.email!!, user.isAdmin!!)
        sendEmailToUser.setOnClickListener {
            mainNavigator.initEmailFragment("", user.email!!)
        }
    }

    override fun onGetUserFail(string: String) {
        Toasty.error(context!!, string).show()
    }
}