package com.example.movies.app

import com.example.movies.BuildConfig
import com.example.movies.model.DatabaseService
import com.example.movies.model.QueriesInterface
import com.example.movies.repository.RemoteRepository
import com.example.movies.repository.Repository
import com.example.movies.repository.TMDbApi
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injection {

    fun provideRepository() : Repository = RemoteRepository


    private fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    fun provideTMDbApi() : TMDbApi {
        return provideRetrofit().create(TMDbApi::class.java)
    }

    private fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    private fun provideOkHttpClient() : OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(provideLoggingInterceptor())
            .addNetworkInterceptor(StethoInterceptor())
        return httpClient.build()
    }
}