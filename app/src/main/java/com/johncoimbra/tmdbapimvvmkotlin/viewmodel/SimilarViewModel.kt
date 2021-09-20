package com.johncoimbra.tmdbapimvvmkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieModel
import com.johncoimbra.tmdbapimvvmkotlin.model.repository.MovieRepository
import com.johncoimbra.tmdbapimvvmkotlin.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SimilarViewModel : ViewModel() {

    private val repository = MovieRepository()
    val similar = MutableLiveData<List<MovieModel>>()
    val similarError = MutableLiveData<Event<Unit>>()

    fun getSimilar(movieId: Int, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieResponse = repository.getSimilar(movieId, page)
            if (movieResponse == null) {
                similarError.postValue(Event(Unit))
            } else {
                similar.postValue(movieResponse.movies)
            }
        }
    }
}