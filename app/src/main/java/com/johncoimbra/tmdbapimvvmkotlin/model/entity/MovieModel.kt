package com.johncoimbra.tmdbapimvvmkotlin.model.entity

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id") val movie_id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("vote_count") val vote_count: Int
)
