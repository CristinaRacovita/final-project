package com.example.moviepicker.domain.items

import java.io.Serializable

data class AllGroupsItem(
    val id: Int?,
    val name: String,
    val users: List<UserDetailsItem>,
) : Serializable
