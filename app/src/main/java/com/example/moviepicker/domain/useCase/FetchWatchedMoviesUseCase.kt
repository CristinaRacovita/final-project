package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.items.WatchedMovieItem
import com.example.moviepicker.domain.mediator.MovieMediator

class FetchWatchedMoviesUseCase(private val mediator: MovieMediator) {
    fun getWatchedMovies(id: Int): LiveData<List<WatchedMovieItem>> {
        return mediator.getWatchedMovies(id)
    }
}