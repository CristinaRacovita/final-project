package com.example.moviepicker.domain.repository

import com.example.moviepicker.data.RatingDTO

interface RatingRepository {
    fun addRatingsForUser(ratings: List<RatingDTO>)
}