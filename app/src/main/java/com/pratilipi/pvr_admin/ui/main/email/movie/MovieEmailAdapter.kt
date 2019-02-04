package com.pratilipi.pvr_admin.ui.main.email.movie

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import com.pratilipi.pvr_admin.R
import com.pratilipi.pvr_admin.data.models.Movie


class MovieEmailAdapter: RecyclerView.Adapter<MovieViewHolder>(),
    MovieViewHolder.OnMovieClickListener {

    private lateinit var list: List<Movie>
    private lateinit var onItemClickListener: OnItemClickListener

    override fun onMovieClick(position: Int, movieViewHolder: MovieViewHolder) {
        onItemClickListener.onItemClick(list[position])
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val view = layoutInflater.inflate(R.layout.item_movie, p0, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: MovieViewHolder, p1: Int) {
        p0.setMovie(list[p1])
        p0.setOnMovieClickListener(this)
    }

    fun setList(list: List<Movie>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }
}