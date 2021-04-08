package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.items.DetailsMovieItem
import com.example.moviepicker.domain.mediator.MovieMediator

class FetchMovieDetailsUseCase(private val mediator: MovieMediator) {
    fun getPickedMovies(ids: String): LiveData<List<DetailsMovieItem>> {
        return mediator.getDetailsForPickedMovies(ids)
    }
}