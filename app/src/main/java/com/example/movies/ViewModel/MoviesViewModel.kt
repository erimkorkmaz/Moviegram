package com.example.movies.ViewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movies.app.Injection
import com.example.movies.model.Movie

class MoviesViewModel : ViewModel()    {
    private val repository = Injection.provideRepository()
    var movieLiveData : LiveData<List<Movie>>

    init {
      movieLiveData=  repository.getMovies()
    }
}