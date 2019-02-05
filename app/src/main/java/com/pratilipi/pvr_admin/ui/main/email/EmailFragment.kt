package com.pratilipi.pvr_admin.ui.main.email

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
import com.pratilipi.pvr_admin.data.models.EmailRequest
import com.pratilipi.pvr_admin.data.remote.ApiInstance
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.email_fragment.*
import org.w3c.dom.Text

class EmailFragment: Fragment(), ApiInstance.OnSendEmailMovieListener, ApiInstance.OnSendEmailUserListener {

    companion object {
        fun newInstance() = EmailFragment()
    }

    private lateinit var mainNavigator: MainNavigator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.email_fragment, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainNavigator = context as MainNavigator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie: String = arguments!!.getString("movie")!!
        val user: String = arguments!!.getString("user")!!
        send.setOnClickListener {
            if(!TextUtils.isEmpty(subject.text) && !TextUtils.isEmpty(email.text)) {
                val emailRequest = EmailRequest()
                emailRequest.subject = subject.text.toString()
                emailRequest.text = email.text.toString()
                emailRequest.from = AdminManager.getInstance().getUser()!!.email
                if(TextUtils.isEmpty(movie)) {
                    emailRequest.to = user
                    ApiInstance.getInstance().sendEmailForUser(emailRequest, this)
                } else if(TextUtils.isEmpty(user)) {
                    emailRequest.movieName = movie
                    ApiInstance.getInstance().sendEmailForMovie(emailRequest, this)
                }
            }
        }
    }

    override fun onSendEmailUserSuccess(string: String) {
        Toasty.success(context!!, string).show()
        activity!!.onBackPressed()
    }

    override fun onSendEmailUserFail(string: String) {
        Toasty.error(context!!, string).show()
    }

    override fun onSendEmailMovieSuccess(string: String) {
        Toasty.success(context!!, string).show()
        activity!!.onBackPressed()
    }

    override fun onSendEmailMovieFail(string: String) {
        Toasty.error(context!!, string).show()
    }
}