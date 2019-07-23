package com.example.movies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.list_item_movies.view.*

class MoviesAdapter(private val movies: MutableList<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movies,parent,false))
    }

    override fun getItemCount() = movies.size

    fun updateMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(movies[position])
    }


    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var movie: Movie

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            this.movie = movie
            itemView.movieTitle.text =movie.title

            GlideApp.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w342/${movie.posterPath}")
                .into(itemView.moviesImage)
        }

        override fun onClick(view: View) {
            val intent = MovieDetailActivity.newIntent(view.context, movie)
            view.context.startActivity(intent)
        }

    }
}