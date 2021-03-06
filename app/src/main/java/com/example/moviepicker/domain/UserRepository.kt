package com.example.moviepicker.domain

import com.example.moviepicker.data.UserDTO

interface UserRepository {
    fun getCredentials(): List<UserDTO>
    fun createNewUser(userDTO: UserDTO): UserDTO
}