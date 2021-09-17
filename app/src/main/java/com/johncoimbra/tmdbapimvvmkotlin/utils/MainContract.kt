package com.johncoimbra.tmdbapimvvmkotlin.utils

interface MainContract {
    interface UserActionListener {
        fun loadNowPlayingMovies(page: Int)
    }
}