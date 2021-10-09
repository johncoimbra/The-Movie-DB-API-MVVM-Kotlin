package com.johncoimbra.tmdbapimvvmkotlin.model


import com.johncoimbra.tmdbapimvvmkotlin.model.service.Credentials
import com.johncoimbra.tmdbapimvvmkotlin.model.service.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    fun makeRetrofit(): MovieApi = Retrofit.Builder()
        .baseUrl(Credentials.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)
}

