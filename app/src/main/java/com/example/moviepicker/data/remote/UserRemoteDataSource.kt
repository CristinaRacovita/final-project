package com.example.moviepicker.data.remote

import com.example.moviepicker.data.dtos.ImageDTO
import com.example.moviepicker.data.dtos.UserDTO
import com.example.moviepicker.data.dtos.UserDetailsDTO
import com.example.moviepicker.domain.repository.UserRepository
import okhttp3.MultipartBody
import java.util.*

class UserRemoteDataSource(private val api: MoviePickerAPI) : UserRepository {

    override fun getCredentials(): List<UserDetailsDTO> {
        val credentials = api.getCredentials().execute().body()
        if (credentials != null) {
            return credentials
        }
        return Collections.emptyList()
    }

    override fun createNewUser(userDTO: UserDTO): UserDTO {
        val user = api.createNewUser(userDTO).execute().body()
        if (user != null) {
            return user
        }

        throw Exception("Some errors in user creation process.")
    }

    override fun uploadPhoto(id: Int, photo: MultipartBody.Part): String {
        return api.uploadPhoto(id, photo).execute().body()!!
    }

    override fun getProfileImage(id: Int): ImageDTO {
        val profileImage = api.getProfileImage(id).execute().body()
        if (profileImage != null) {
            return profileImage
        }

        throw Exception("Can't get image profile")
    }

    override fun getUsersDetails(ids: String): List<UserDetailsDTO> {
        val usersDetails = api.getUsersDetails(ids).execute().body()
        if (usersDetails != null) {
            return usersDetails
        }

        return Collections.emptyList()
    }

    override fun checkUser(userDTO: UserDTO): UserDTO {
        return api.checkUser(userDTO).execute().body()!!
    }
}