package com.example.moviepicker.data.dtos

import com.google.gson.annotations.SerializedName

data class RecommendedMovieDTO(
    @SerializedName("movie_id") val id: Int,
    @SerializedName("movie_title") val title: String,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("description_en") val descriptionEn: String?,
    @SerializedName("description_ro") val descriptionRo: String?
)