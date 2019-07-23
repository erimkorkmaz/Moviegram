package com.example.movies.model

import androidx.lifecycle.LiveData

interface QueriesInterface {

    fun getAllFavorites () :LiveData<List<Movie>>

    fun insertFavorites(movie: Movie)

    fun checkIfMovieAvailable(movieId: Int): Boolean

    fun deleteFavouriteMovie(movieId: Int)

}