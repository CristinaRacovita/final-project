package com.example.moviepicker.data

import com.google.gson.annotations.SerializedName

data class RatingDTO(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("movie_id") val movieId: Int,
    @SerializedName("rating") val rating: Float
)