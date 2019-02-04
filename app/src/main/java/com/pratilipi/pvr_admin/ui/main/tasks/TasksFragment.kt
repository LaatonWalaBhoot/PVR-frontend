package com.pratilipi.pvr_admin.ui.main.tasks

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pratilipi.pvr_admin.MainNavigator
import com.pratilipi.pvr_admin.R
import kotlinx.android.synthetic.main.tasks_fragment.*

class TasksFragment: Fragment() {

    companion object {
        fun newInstance() = TasksFragment()
    }

    private lateinit var mainNavigator: MainNavigator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.tasks_fragment, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainNavigator = context as MainNavigator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createUser.setOnClickListener {
            mainNavigator.initCreateUserFragment()
        }
        sendEmailToUser.setOnClickListener {
            mainNavigator.initUserEmailFragment()

        }
        sendEmailForMovie.setOnClickListener {
            mainNavigator.initMovieEmailFragment()
        }
    }

}