package com.example.moviepicker.data.remote

import com.example.moviepicker.data.UserDTO
import com.example.moviepicker.domain.repository.UserRepository
import java.util.*

class UserRemoteDataSource(private val api: MoviePickerAPI) : UserRepository {

    override fun getCredentials(): List<UserDTO> {
        val credentials = api.getCredentials().execute().body()
        if (credentials != null) {
            return credentials
        }
        return Collections.emptyList()
    }

    override fun createNewUser(userDTO: UserDTO): UserDTO {
        val user  =  api.createNewUser(userDTO).execute().body()
        if(user != null){
            return user
        }

        throw Exception("Some errors in user creation process.")
    }

}