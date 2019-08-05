package com.example.movies.ui


import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movies.R
import com.example.movies.model.Movie
import kotlinx.android.synthetic.main.list_item_favorites.view.*

class MoviesAdapter(val movies: MutableList<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movies,parent,false))
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
            Glide.with(itemView.context)
                .load(url).apply(
                    RequestOptions().centerCrop().transform(RoundedCorners(24))
                        .placeholder(AppCompatResources.getDrawable(itemView.context,R.drawable.ic_image_place_holder))

                )
                .into(itemView.moviesImage)

            animateView(itemView)
        }

        override fun onClick(view: View) {
            val intent = MovieDetailActivity.newIntent(view.context, movie)
            view.context.startActivity(intent)
        }

        private fun animateView(viewToAnimate : View) {
            if(viewToAnimate.animation == null) {
                val animation = AnimationUtils.loadAnimation(viewToAnimate.context,R.anim.scale_xy)
                viewToAnimate.animation = animation
            }
        }
    }

}