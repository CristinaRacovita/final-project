package com.example.moviepicker.domain.builder

import com.example.moviepicker.data.dtos.UserDTO
import com.example.moviepicker.domain.items.UserItem

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