package com.example.movies.ui


import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movies.R
import com.example.movies.model.Movie
import com.example.movies.ui.MainActivity.Companion.database
import com.muddzdev.styleabletoast.StyleableToast
import com.shashank.sony.fancytoastlib.FancyToast
import com.tapadoo.alerter.Alerter
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
        setupFavoriteButton()
    }

    private fun setupView() {

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342/${movie.posterPath}").apply(
                RequestOptions().centerCrop().transform(RoundedCorners(32))
                    .placeholder(R.drawable.ic_image_place_holder)
                    .error(R.drawable.ic_broken_image)
                    .fallback(R.drawable.ic_no_image)
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

    private fun setupFavoriteButton() {
        favoriteButton.scale = 1.5f
        setupFavoriteButtonClickListener(movie)
        if (database.checkIfMovieAvailable(movie.id))  favoriteButton.progress = 1f else favoriteButton.progress = 0f


    }
    private fun setupFavoriteButtonClickListener(movie: Movie) {
        favoriteButton.setOnClickListener {
            if (database.checkIfMovieAvailable(movie.id)) {
                playReverseFavoriteAnimation(favoriteButton)
                database.deleteFavouriteMovie(movie.id)

 //               FancyToast.makeText(this,"Removed from favorites",Toast.LENGTH_SHORT,FancyToast.ERROR,false).show()
 //               StyleableToast.makeText(this, "Removed from favorites", Toast.LENGTH_LONG, R.style.myToast).show()
                  StyleableToast
                      .Builder(this)
                      .text("Removed from favorites")
                      .textColor(getResources().getColor(R.color.primaryTextColor))
                      .backgroundColor(getResources().getColor(R.color.magenta))
                      .show()
//                Alerter.create(this)
//                    .setBackgroundColorRes(R.color.secondaryColor)
//                    .setText("Removed from favorites")
//                    .setEnterAnimation(R.anim.abc_grow_fade_in_from_bottom)
//                    .setExitAnimation(R.anim.abc_shrink_fade_out_from_bottom)
//                    .show()
            } else {
                favoriteButton.playAnimation()
                database.insertFavorites(movie)
   //             FancyToast.makeText(this,"Added to favorites ",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
                StyleableToast
                    .Builder(this)
                    .text("Added to favorites")
                    .textColor(getResources().getColor(R.color.primaryTextColor))
                    .backgroundColor(getResources().getColor(R.color.secondaryColor))
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
