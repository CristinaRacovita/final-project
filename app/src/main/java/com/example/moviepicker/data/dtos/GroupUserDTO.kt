package com.example.moviepicker.data.dtos

import com.google.gson.annotations.SerializedName

data class GroupUserDTO(
    @SerializedName("group_user_id") val id: Int? = 0,
    @SerializedName("group_id") val groupId: Int,
    @SerializedName("user_id") val userId: Int
)
