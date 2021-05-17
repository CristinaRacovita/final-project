package com.example.moviepicker.data.remote

import com.example.moviepicker.data.dtos.RatingDTO
import com.example.moviepicker.domain.repository.RatingRepository

class RatingRemoteSource(private val api: MoviePickerAPI) : RatingRepository {
    override fun addRatingsForUser(ratings: List<RatingDTO>) {
        api.rateMovies(ratings).execute().body()!!
    }
}