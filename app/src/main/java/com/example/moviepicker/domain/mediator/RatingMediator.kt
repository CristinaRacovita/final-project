package com.example.moviepicker.domain.mediator

import com.example.moviepicker.domain.builder.RatingBuilder
import com.example.moviepicker.domain.items.RatingItem
import com.example.moviepicker.domain.repository.RatingRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.stream.Collectors

class RatingMediator(private val remoteRepository: RatingRepository) {
    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun addRatings(ratings: List<RatingItem>) {
        executorService.execute {
            remoteRepository.addRatingsForUser(
                ratings.stream().map(RatingBuilder::toDTO).collect(Collectors.toList())
            )
        }
    }
}