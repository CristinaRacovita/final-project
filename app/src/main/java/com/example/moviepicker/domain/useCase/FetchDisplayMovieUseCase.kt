package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.mediator.MovieMediator

class FetchDisplayMovieUseCase(private val mediator: MovieMediator) {
    fun getMovies(): LiveData<List<DisplayMovieItem>> {
        return mediator.getMovies()
    }
}