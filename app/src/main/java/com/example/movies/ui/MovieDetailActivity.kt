package com.example.movies.ui


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.movies.R
import com.example.movies.model.Movie
import com.example.movies.ui.MainActivity.Companion.database
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.fragment_movies.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movie : Movie

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"


        fun newIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_ID, movie)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movie = intent.getParcelableExtra(MOVIE_ID)!!

        detailProgressBar.visibility = View.VISIBLE

        setSupportActionBar(detailToolbar)
        val actionBar = supportActionBar
        actionBar?.title = movie.title
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        setupView()
        setupFavoriteButton()
    }

    private fun setupView() {

        GlideApp.with(this)
            .load("https://image.tmdb.org/t/p/w342/${movie.posterPath}")
            .placeholder(R.drawable.ic_image_place_holder)
            .error(R.drawable.ic_broken_image)
            .fallback(R.drawable.ic_no_image)
            .into(movieDetailImage)

        movieDetailTitle.text = movie.title
        movieVote.text = movie.vote.toString()
        movieReleaseDate.text = String.format("Release Date : ${movie.releaseDate}")
        movieIsAdult.text = if(movie.isAdult) String.format("+18") else String.format("General Viewers")
        movieOverview.text = movie.overview
        detailProgressBar.visibility = View.GONE
    }

    private fun setupFavoriteButton() {
        setupFavoriteButtonImage(movie)
        setupFavoriteButtonClickListener(movie)
    }

    private fun setupFavoriteButtonImage(movie: Movie) {
        if (database.checkIfMovieAvailable(movie.id)) {
            favoriteButton.setImageDrawable(getDrawable(R.drawable.ic_star_black_24dp))
        } else {
            favoriteButton.setImageDrawable(getDrawable(R.drawable.ic_star_border_black_24dp))
        }
    }

    private fun setupFavoriteButtonClickListener(movie: Movie) {
        favoriteButton.setOnClickListener { _ ->
            if (database.checkIfMovieAvailable(movie.id)) {
                favoriteButton.setImageDrawable(getDrawable(R.drawable.ic_star_border_black_24dp))
                database.deleteFavouriteMovie(movie.id)
                Toast.makeText(this,"Removed from favorites ",Toast.LENGTH_SHORT).show()
            } else {
                favoriteButton.setImageDrawable(getDrawable(R.drawable.ic_star_black_24dp))
                database.insertFavorites(movie)
                Toast.makeText(this,"Added to favorites ",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }



}
