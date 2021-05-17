package com.example.moviepicker.domain.builder

import com.example.moviepicker.data.dtos.GroupMovieDTO
import com.example.moviepicker.domain.items.GroupMovieItem

class GroupMovieBuilder {
    companion object {
        fun toDTO(groupItem: GroupMovieItem): GroupMovieDTO {
            return GroupMovieDTO(groupItem.groupId, groupItem.movieId)
        }

        fun toItem(groupDTO: GroupMovieDTO): GroupMovieItem {
            return GroupMovieItem(groupDTO.groupId, groupDTO.movieId)
        }
    }
}