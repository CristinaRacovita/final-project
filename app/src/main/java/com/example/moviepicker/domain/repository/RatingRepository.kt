package com.example.moviepicker.domain.repository

import com.example.moviepicker.data.dtos.RatingDTO

interface RatingRepository {
    fun addRatingsForUser(ratings: List<RatingDTO>)
}