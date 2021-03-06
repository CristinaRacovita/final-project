package com.example.moviepicker.data

import com.google.gson.annotations.SerializedName

data class UserDTO(@SerializedName("user_id") val id: Int? = 0, @SerializedName("username") val email: String, val password: String)