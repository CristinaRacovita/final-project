package com.example.moviepicker.domain.builder

import com.example.moviepicker.data.dtos.UserDetailsDTO
import com.example.moviepicker.domain.items.UserDetailsItem

class UserDetailsBuilder {
    companion object {
        fun toDTO(userDetailsItem: UserDetailsItem): UserDetailsDTO {
            return UserDetailsDTO(
                userDetailsItem.id,
                userDetailsItem.email,
                userDetailsItem.profileImage
            )
        }

        fun toItem(userDetailsDTO: UserDetailsDTO): UserDetailsItem {
            return UserDetailsItem(
                userDetailsDTO.id,
                userDetailsDTO.email,
                userDetailsDTO.profileImage
            )
        }
    }
}