package com.johncoimbra.tmdbapimvvmkotlin.model.service

import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("/3/movie/{movie_id}/similar?")
    suspend fun getSimilar(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResponse>
}



