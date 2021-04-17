package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.data.RecommendedMovieDTO
import com.example.moviepicker.domain.items.RecommendedMovieItem
import com.example.moviepicker.domain.mediator.MovieMediator

class GetRecommendedMovieUseCase(private val mediator: MovieMediator) {
    fun getRecommended(id: Int): LiveData<List<RecommendedMovieItem>> {
        return mediator.getRecommendedMovie(id)
    }
}