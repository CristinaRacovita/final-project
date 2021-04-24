package com.example.moviepicker.domain.repository

import com.example.moviepicker.data.ImageDTO
import com.example.moviepicker.data.UserDTO
import okhttp3.MultipartBody

interface UserRepository {
    fun getCredentials(): List<UserDTO>
    fun createNewUser(userDTO: UserDTO): UserDTO
    fun uploadPhoto(id: Int, photo: MultipartBody.Part): String
    fun getProfileImage(id: Int): ImageDTO
}