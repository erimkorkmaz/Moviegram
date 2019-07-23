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

class MoviesCardAdapter(private val movies: MutableList<Movie>) : RecyclerView.Adapter<MoviesCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movies_card,parent,false))
    }

    override fun getItemCount() = movies.size

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
            
            val url = if(movie.posterPath != null) "https://image.tmdb.org/t/p/w342/${movie.posterPath}" else null
            GlideApp.with(itemView.context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_image_place_holder)
                .error(R.drawable.ic_broken_image)
                .fallback(R.drawable.ic_no_image)
                .into(itemView.moviesImage)
        }

        override fun onClick(view: View) {
            val intent = MovieDetailActivity.newIntent(view.context, movie)
            view.context.startActivity(intent)
        }

    }
}