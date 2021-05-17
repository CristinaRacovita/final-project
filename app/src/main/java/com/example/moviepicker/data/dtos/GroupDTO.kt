package com.example.moviepicker.data.dtos

import com.google.gson.annotations.SerializedName

data class GroupDTO(
    @SerializedName("group_id") val id: Int? = 0,
    @SerializedName("group_name") val name: String
)
