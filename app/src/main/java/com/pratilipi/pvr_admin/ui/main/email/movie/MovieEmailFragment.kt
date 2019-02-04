package com.pratilipi.pvr_admin.ui.main.email.movie

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pratilipi.pvr_admin.MainNavigator
import com.pratilipi.pvr_admin.R
import com.pratilipi.pvr_admin.data.models.EmailRequest
import com.pratilipi.pvr_admin.data.models.Movie
import com.pratilipi.pvr_admin.data.remote.ApiInstance
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.movie_email_fragment.*

class MovieEmailFragment: Fragment(),
    MovieEmailAdapter.OnItemClickListener, ApiInstance.OnGetMoviesListener {

    companion object {
        fun newInstance() = MovieEmailFragment()
    }

    private lateinit var mainNavigator: MainNavigator
    private lateinit var movieEmailAdapter: MovieEmailAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.movie_email_fragment, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainNavigator = context as MainNavigator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApiInstance.getInstance().getAllMovies(this)
        movieEmailAdapter = MovieEmailAdapter()
        movie_list.layoutManager = LinearLayoutManager(context)
        movie_list.adapter = movieEmailAdapter
        movieEmailAdapter.setOnItemClickListener(this)
    }

    override fun onItemClick(movie: Movie) {
        mainNavigator.initEmailFragment(movie.name!!, "")
    }

    override fun onGetMoviesSuccess(list: List<Movie>) {
        progress_bar.visibility = View.GONE
        movie_list.visibility = View.VISIBLE
        movieEmailAdapter.setList(list)
    }

    override fun onGetMoviesFail(string: String) {
        activity!!.onBackPressed()
        Toasty.error(context!!, string).show()
    }
}