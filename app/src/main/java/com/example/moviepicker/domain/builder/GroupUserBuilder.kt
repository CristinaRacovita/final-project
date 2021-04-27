package com.example.moviepicker.domain.builder

import com.example.moviepicker.data.dtos.GroupUserDTO
import com.example.moviepicker.domain.items.GroupUserItem

class GroupUserBuilder {
    companion object {
        fun toDTO(groupItem: GroupUserItem): GroupUserDTO {
            return GroupUserDTO(groupId = groupItem.groupId, userId = groupItem.userId)
        }

        fun toItem(groupDTO: GroupUserDTO): GroupUserItem {
            return GroupUserItem(groupId = groupDTO.groupId, userId = groupDTO.userId)
        }
    }
}