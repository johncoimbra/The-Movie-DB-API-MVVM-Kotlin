package com.johncoimbra.tmdbapimvvmkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieModel
import com.johncoimbra.tmdbapimvvmkotlin.model.repository.MovieRepository
import com.johncoimbra.tmdbapimvvmkotlin.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NowPlayingViewModel : ViewModel() {

    private val repository = MovieRepository()
    val nowPlaying = MutableLiveData<List<MovieModel>>()
    val nowPlayingError = MutableLiveData<Event<Unit>>()

    fun getNowPlaying(language: String, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieResponse = repository.getNowPlaying(language = language, page = page)
            if (movieResponse == null) {
                nowPlayingError.postValue(Event(Unit))
            } else {
                nowPlaying.postValue(movieResponse.movies)
            }
        }
    }
}