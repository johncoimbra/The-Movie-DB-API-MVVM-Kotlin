package com.johncoimbra.tmdbapimvvmkotlin.model.repository

import com.johncoimbra.tmdbapimvvmkotlin.model.RetrofitFactory
import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieResponse
import com.johncoimbra.tmdbapimvvmkotlin.model.service.Credentials
import com.johncoimbra.tmdbapimvvmkotlin.model.service.MovieApi

class MovieRepository {

    private val api: MovieApi = RetrofitFactory.makeRetrofit()

    suspend fun getNowPlaying(language: String, page: Int): MovieResponse? {
        val response = api.getNowPlaying(Credentials.API_KEY, language, page)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun getSimilar(movieId: Int, language: String, page: Int): MovieResponse? {
        val response = api.getSimilar(movieId, Credentials.API_KEY, language, page)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}