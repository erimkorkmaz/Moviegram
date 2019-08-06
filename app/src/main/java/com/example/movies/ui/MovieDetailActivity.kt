package com.example.movies.ui


import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movies.R
import com.example.movies.model.Movie
import com.example.movies.ui.MainActivity.Companion.database
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.activity_movie_detail.*

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

        detailLoading.visibility = View.VISIBLE
        detailLoading.setAnimation("loading.json")
        detailLoading.playAnimation()
        detailLoading.loop(true)

        movie = intent.getParcelableExtra(MOVIE_ID)!!


        setSupportActionBar(detailToolbar)
        val actionBar = supportActionBar
        actionBar?.title = movie.title
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        setupView()
        setupFavoriteButton(movie)
    }

    private fun setupView() {

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342/${movie.posterPath}").apply(
                RequestOptions().centerCrop().transform(RoundedCorners(32))
                    .placeholder(R.drawable.ic_image_place_holder)
                    .error(R.drawable.ic_broken_image)
  //                  .fallback(R.drawable.ic_no_image)
            )
            .into(movieDetailImage)

        movieDetailTitle.text = movie.title
        movieVote.text = movie.vote.toString()
        moviePopularity.text = String.format("Popularity : ${movie.popularity}")
        movieReleaseDate.text = String.format("Release Date : ${movie.releaseDate}")
        movieIsAdult.text = if(movie.isAdult) String.format("+18") else String.format("General Viewers")
        movieOverview.text = movie.overview
        detailLoading.visibility = View.GONE
    }


    private fun setupFavoriteButton(movie: Movie) {
        favoriteButton.scale = 1.5f
        if (database.checkIfMovieAvailable(movie.id))  favoriteButton.progress = 1f else favoriteButton.progress = 0f
        favoriteButton.setOnClickListener {
            if (database.checkIfMovieAvailable(movie.id)) {
                playReverseFavoriteAnimation(favoriteButton)
                database.deleteFavouriteMovie(movie.id)

                  StyleableToast
                      .Builder(this)
                      .text("Removed from favorites")
                      .textColor(ContextCompat.getColor(this,R.color.primaryTextColor))
                      .font(R.font.manropemedium)
                      .backgroundColor(ContextCompat.getColor(this,R.color.magenta))
                      .show()

            } else {
                favoriteButton.playAnimation()
                database.insertFavorites(movie)
                StyleableToast
                    .Builder(this)
                    .text("Added to favorites")
                    .textColor(ContextCompat.getColor(this,R.color.primaryTextColor))
                    .font(R.font.manropemedium)
                    .backgroundColor(ContextCompat.getColor(this,R.color.secondaryColor))
                    .show()
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

    private fun playReverseFavoriteAnimation(animationView : LottieAnimationView) {
        val progress = 0.5f
        val valueAnimator = ValueAnimator.ofFloat(-progress,0f).setDuration((animationView.duration*progress).toLong())
        valueAnimator.addUpdateListener { animation -> animationView.progress = Math.abs(animation.animatedValue as Float) }
        valueAnimator.start()
    }



}
