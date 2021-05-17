package com.example.moviepicker.data.dtos

import com.google.gson.annotations.SerializedName

data class GroupMovieDTO(
    @SerializedName("group_id") val groupId: Int,
    @SerializedName("movie_id") val movieId: Int
)
