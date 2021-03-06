package com.example.moviepicker.domain

import com.example.moviepicker.data.UserDTO

class UserBuilder {
    companion object {
        fun toDTO(userItem: UserItem): UserDTO {
            return UserDTO(userItem.id, userItem.email, userItem.password)
        }

        fun toItem(userDTO: UserDTO): UserItem {
            return UserItem(userDTO.id, userDTO.email, userDTO.password)
        }
    }
}