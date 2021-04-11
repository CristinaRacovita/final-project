package com.example.moviepicker.data

import com.google.gson.annotations.SerializedName

data class RecommendedMovieDTO(
    @SerializedName("movie_id") val id: Int,
    @SerializedName("movie_title") val title: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("release_date") val releaseDate: String,
)