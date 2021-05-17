package com.example.moviepicker.domain.builder

import com.example.moviepicker.data.dtos.AllGroupsDTO
import com.example.moviepicker.domain.items.AllGroupsItem
import java.util.stream.Collectors

class AllGroupsBuilder {
    companion object {
        fun toDTO(allGroupsItem: AllGroupsItem): AllGroupsDTO {
            return AllGroupsDTO(
                allGroupsItem.id,
                allGroupsItem.name,
                allGroupsItem.users.stream().map(UserDetailsBuilder::toDTO).collect(
                    Collectors.toList()
                )
            )
        }

        fun toItem(allGroupsDTO: AllGroupsDTO): AllGroupsItem {
            return AllGroupsItem(
                allGroupsDTO.id, allGroupsDTO.name,
                allGroupsDTO.users.stream().map(UserDetailsBuilder::toItem).collect(
                    Collectors.toList()
                )
            )
        }
    }

}
