package com.example.movies.repository

import androidx.lifecycle.LiveData
import com.example.movies.model.Movie

interface Repository {
    fun getMovies(): LiveData<List<Movie>>
}