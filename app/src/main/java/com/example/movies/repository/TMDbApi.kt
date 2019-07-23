package com.example.movies.repository

import com.example.movies.model.Movie
import com.example.movies.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TMDbApi {

    @GET("movie/now_playing")
    fun getMovies(@Query("api_key") apiKey: String) : Call<MovieResponse>

}
