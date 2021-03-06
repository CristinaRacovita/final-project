package com.example.moviepicker.data.remote

import com.example.moviepicker.data.UserDTO
import com.example.moviepicker.domain.UserRepository
import java.util.*

class RemoteDataSource(val api: MoviePickerAPI) : UserRepository {

    override fun getCredentials(): List<UserDTO> {
        val credentials = api.getCredentials().execute().body()
        if (credentials != null) {
            return credentials
        }
        return Collections.emptyList()
    }

    override fun createNewUser(userDTO: UserDTO): UserDTO {
        return api.createNewUser(userDTO).execute().body()!!
    }


}