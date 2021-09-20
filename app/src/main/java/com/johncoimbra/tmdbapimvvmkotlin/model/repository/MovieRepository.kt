package com.johncoimbra.tmdbapimvvmkotlin.model.repository

import com.johncoimbra.tmdbapimvvmkotlin.model.RetrofitFactory
import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieResponse
import com.johncoimbra.tmdbapimvvmkotlin.model.service.Credentials
import com.johncoimbra.tmdbapimvvmkotlin.model.service.MovieApi

class MovieRepository {

    private val api: MovieApi = RetrofitFactory.makeRetrofit()

    suspend fun getNowPlaying(page: Int): MovieResponse? {
        val response = api.getNowPlaying(Credentials.API_KEY, page)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun getSimilar(movieId: Int, page: Int): MovieResponse? {
        val response = api.getSimilar(movieId, Credentials.API_KEY, page)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}