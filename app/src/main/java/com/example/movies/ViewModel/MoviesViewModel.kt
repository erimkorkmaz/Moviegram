package com.example.movies.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.movies.app.Injection

class MoviesViewModel(application: Application) : AndroidViewModel(application)    {
    private val repository = Injection.provideRepository()
    private val allMovies = repository.getMovies()

    fun getMovies() = allMovies
}