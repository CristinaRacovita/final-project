package com.example.moviepicker.data.dtos

import com.google.gson.annotations.SerializedName

data class AllGroupsDTO(
    @SerializedName("group_id") val id: Int?,
    @SerializedName("group_name") val name: String,
    @SerializedName("users") val users: List<UserDetailsDTO>,
)