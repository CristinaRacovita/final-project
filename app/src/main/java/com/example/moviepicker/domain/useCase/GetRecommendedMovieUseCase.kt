package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.mediator.MovieMediator

class GetRecommendedMovieUseCase(private val mediator: MovieMediator) {
    fun getRecommended(id: Int): LiveData<String> {
        return mediator.getRecommendedMovie(id)
    }
}