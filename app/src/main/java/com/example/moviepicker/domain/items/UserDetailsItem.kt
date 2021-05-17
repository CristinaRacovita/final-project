package com.example.moviepicker.domain.items

import java.io.Serializable

data class UserDetailsItem(
    val id: Int?,
    val email: String?,
    val profileImage: String?
) : Serializable
