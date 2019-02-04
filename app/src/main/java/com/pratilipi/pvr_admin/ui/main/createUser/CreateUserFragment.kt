package com.pratilipi.pvr_admin.ui.main.createUser

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pratilipi.pvr_admin.MainNavigator
import com.pratilipi.pvr_admin.R
import com.pratilipi.pvr_admin.data.models.City
import com.pratilipi.pvr_admin.data.models.User
import com.pratilipi.pvr_admin.data.remote.ApiInstance
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.sign_up_fragment.*

class CreateUserFragment: Fragment(), ApiInstance.OnGetCitiesListener, ApiInstance.OnCreateUserListener {

    companion object {
        fun newInstance() = CreateUserFragment()
    }

    private lateinit var mainNavigator: MainNavigator
    private var cityNames: ArrayList<String> = ArrayList()
    private var cities: List<City> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainNavigator = context as MainNavigator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApiInstance.getInstance().getAllCities(this)
        login.visibility = View.GONE
        submit.text = "CREATE"
        submit.setOnClickListener {
            submit.visibility = View.INVISIBLE
            progress_layout.visibility = View.VISIBLE
            checkCredentialsAndCreateUser()
        }
    }

    private fun checkCredentialsAndCreateUser() {
        if(!TextUtils.isEmpty(username.text) && !TextUtils.isEmpty(email.text) && !TextUtils.isEmpty(password.text)) {
            if(TextUtils.equals(password.text, confirm.text)) {
                ApiInstance.getInstance().createUser(username.text.toString(), email.text.toString(), password.text.toString(), cities[nice_spinner.selectedIndex].id!!, this)
            } else {
                confirm.error = "Cannot confirm password"
            }
        }
    }

    override fun onGetCitiesSuccess(list: List<City>) {
        cities = list
        list.forEach {
            cityNames.add(it.name!!)
        }
        nice_spinner.attachDataSource(cityNames)
    }

    override fun onGetCitiesFail(string: String) {
        Toasty.error(context!!, string).show()
    }

    override fun onCreateUserSuccess(user: User) {
        Toasty.success(context!!, "User created successfully").show()
        activity!!.onBackPressed()
    }

    override fun onCreateUserFail(string: String) {
        submit.visibility = View.VISIBLE
        progress_layout.visibility = View.GONE
        Toasty.error(context!!, string).show()
    }

}