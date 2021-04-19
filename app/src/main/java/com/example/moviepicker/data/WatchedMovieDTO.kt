package com.example.moviepicker.data

import com.google.gson.annotations.SerializedName

data class WatchedMovieDTO(
    @SerializedName("movie_title") val title: String,
    @SerializedName("rating") val rating: Float
)