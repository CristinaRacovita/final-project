package com.example.moviepicker.data.dtos

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("user_id") val id: Int? = 0,
    @SerializedName("username") val email: String?,
    @SerializedName("password") val password: String?
)