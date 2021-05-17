package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.mediator.MovieMediator

class FetchUnratedMovies(private val mediator: MovieMediator) {
    fun getUnratedMovies(id: Int): LiveData<List<DisplayMovieItem>> {
        return mediator.getUnratedMovie(id)
    }
}