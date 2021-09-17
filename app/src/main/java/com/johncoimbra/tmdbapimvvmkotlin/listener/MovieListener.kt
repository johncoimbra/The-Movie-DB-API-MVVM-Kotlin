package com.johncoimbra.tmdbapimvvmkotlin.listener

import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieModel

interface MovieListener {
    fun onMovieItemClicked(movieModel: MovieModel)
}