package com.example.movies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.app.Injection
import com.example.movies.model.Movie
import com.example.movies.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RemoteRepository : Repository {

    private const val API = "3e94b6df120a99d34bbe709538a13de2"

    private val api = Injection.provideTMDbApi()

    override fun getMovies(): LiveData<List<Movie>> {
        val liveData = MutableLiveData<List<Movie>>()

        api.getMovies(API).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>?) {
                if (response != null) {
                    liveData.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }
        })
        return liveData
    }
}