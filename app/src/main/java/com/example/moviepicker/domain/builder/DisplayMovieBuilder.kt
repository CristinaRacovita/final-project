package com.example.moviepicker.domain.builder

import com.example.moviepicker.data.dtos.DisplayMovieDTO
import com.example.moviepicker.domain.items.DisplayMovieItem

class DisplayMovieBuilder {
    companion object {
        fun toDTO(displayMovieItem: DisplayMovieItem): DisplayMovieDTO {
            return DisplayMovieDTO(displayMovieItem.id, displayMovieItem.title)
        }

        fun toItem(displayMovieDTO: DisplayMovieDTO): DisplayMovieItem {
            return DisplayMovieItem(displayMovieDTO.id, displayMovieDTO.title)
        }
    }
}