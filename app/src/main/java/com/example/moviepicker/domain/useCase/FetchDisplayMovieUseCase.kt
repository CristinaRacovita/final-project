package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.mediator.DisplayMovieMediator

class FetchDisplayMovieUseCase(private val mediator: DisplayMovieMediator) {
    fun getMovies(): LiveData<List<DisplayMovieItem>> {
        return mediator.getMovies()
    }
}