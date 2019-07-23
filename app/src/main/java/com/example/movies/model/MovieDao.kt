package com.example.movies.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao   {

    @Insert
    fun insert(movie:Movie)

    @Delete
    fun delete(vararg movie: Movie)

    @Query("SELECT * FROM movies")
    fun getAllMovies() : LiveData<List<Movie>>

    @Query("Delete FROM movies where id = :movieId")
    fun deleteMovie(movieId: Int)

    @Query("Select * FROM movies where id = :movieId")
    fun checkIfMovieIsFavourite(movieId: Int): Boolean

}