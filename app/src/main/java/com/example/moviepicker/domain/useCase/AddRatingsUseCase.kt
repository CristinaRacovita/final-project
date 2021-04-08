package com.example.moviepicker.domain.useCase

import com.example.moviepicker.domain.items.RatingItem
import com.example.moviepicker.domain.mediator.RatingMediator

class AddRatingsUseCase(private val mediator: RatingMediator) {
    fun addRatings(ratings: List<RatingItem>) {
        return mediator.addRatings(ratings)
    }
}