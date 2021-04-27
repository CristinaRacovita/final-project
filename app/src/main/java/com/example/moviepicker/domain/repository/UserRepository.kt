package com.example.moviepicker.domain.repository

import com.example.moviepicker.data.dtos.ImageDTO
import com.example.moviepicker.data.dtos.UserDTO
import com.example.moviepicker.data.dtos.UserDetailsDTO
import okhttp3.MultipartBody

interface UserRepository {
    fun getCredentials(): List<UserDetailsDTO>
    fun createNewUser(userDTO: UserDTO): UserDTO
    fun uploadPhoto(id: Int, photo: MultipartBody.Part): String
    fun getProfileImage(id: Int): ImageDTO
    fun getUsersDetails(ids: String): List<UserDetailsDTO>
    fun getUsers(): List<UserDTO>
}