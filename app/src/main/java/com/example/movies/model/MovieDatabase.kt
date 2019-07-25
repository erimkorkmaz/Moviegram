package com.example.movies.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Movie::class), version = 4,exportSchema = false)
abstract class MovieDatabase : RoomDatabase()  {
    abstract fun movieDao() : MovieDao

    companion object {

        private var movieDatabase: MovieDatabase? = null


        fun getInstance(context: Context): MovieDatabase {
            if (movieDatabase == null) {
                movieDatabase =
                    Room.databaseBuilder(context.getApplicationContext(), MovieDatabase::class.java, "movie_db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return movieDatabase!!
        }
    }
}