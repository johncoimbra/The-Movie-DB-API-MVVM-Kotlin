package com.johncoimbra.tmdbapimvvmkotlin.model.entity

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") val movies: List<MovieModel>,
)