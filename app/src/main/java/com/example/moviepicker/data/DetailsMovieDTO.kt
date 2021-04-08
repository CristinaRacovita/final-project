package com.example.moviepicker.data

import com.google.gson.annotations.SerializedName

data class DetailsMovieDTO(
    @SerializedName("movie_id") val id: Int,
    @SerializedName("movie_title") val title: String,
    @SerializedName("image_url") val imageUrl: String,
)