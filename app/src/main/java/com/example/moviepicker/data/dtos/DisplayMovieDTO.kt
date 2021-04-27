package com.example.moviepicker.data.dtos

import com.google.gson.annotations.SerializedName

data class DisplayMovieDTO(
    @SerializedName("movie_id") val id: Int,
    @SerializedName("movie_title") val title: String
)