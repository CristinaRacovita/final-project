package com.example.moviepicker.data.dtos

import com.google.gson.annotations.SerializedName

data class UserDetailsDTO(
    @SerializedName("user_id") val id: Int?,
    @SerializedName("username") val email: String?,
    @SerializedName("profile_image") val profileImage: String?
)