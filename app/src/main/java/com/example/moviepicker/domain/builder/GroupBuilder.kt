package com.example.moviepicker.domain.builder

import com.example.moviepicker.data.dtos.GroupDTO
import com.example.moviepicker.domain.items.GroupItem

class GroupBuilder {
    companion object {
        fun toDTO(groupItem: GroupItem): GroupDTO {
            return GroupDTO(groupItem.id, groupItem.name)
        }

        fun toItem(groupDTO: GroupDTO): GroupItem {
            return GroupItem(groupDTO.id, groupDTO.name)
        }
    }
}