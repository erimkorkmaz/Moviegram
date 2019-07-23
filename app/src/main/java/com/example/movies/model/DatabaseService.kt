package com.example.movies.model

import android.content.Context
import androidx.lifecycle.LiveData

class DatabaseService(context: Context) : QueriesInterface {

    private val dao: MovieDao = MovieDatabase.getInstance(context).movieDao()

    override fun getAllFavorites(): LiveData<List<Movie>> {
        return dao.getAllMovies()
    }

    override fun insertFavorites(movie: Movie) {
        return dao.insert(movie)

    }

    override fun checkIfMovieAvailable(movieId: Int): Boolean {
        return dao.checkIfMovieIsFavourite(movieId)
    }

    override fun deleteFavouriteMovie(movieId: Int) {
        return dao.deleteMovie(movieId)

    }


}