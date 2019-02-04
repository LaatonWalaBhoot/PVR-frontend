package com.pratilipi.pvr_admin.ui.main.email.movie

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import com.pratilipi.pvr_admin.R
import com.pratilipi.pvr_admin.data.models.Movie

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var name: TextView = itemView.findViewById(R.id.name)
    private lateinit var onMovieClickListner: OnMovieClickListener

    override fun onClick(v: View?) {
        onMovieClickListner.onMovieClick(adapterPosition, this)
    }

    init {
       itemView.setOnClickListener(this)
    }

    fun setMovie(movie: Movie) {
        name.text = movie.name
    }

    fun setOnMovieClickListener(onMovieClickListner: OnMovieClickListener) {
        this.onMovieClickListner = onMovieClickListner
    }

    interface OnMovieClickListener {
        fun onMovieClick(position: Int, movieViewHolder: MovieViewHolder)
    }
}