package com.example.moviepicker.domain.builder

import com.example.moviepicker.data.DetailsMovieDTO
import com.example.moviepicker.domain.items.DetailsMovieItem

class DetailsMovieBuilder {
    companion object {
        fun toDTO(detailsMovie: DetailsMovieItem): DetailsMovieDTO {
            return DetailsMovieDTO(
                detailsMovie.id,
                detailsMovie.title,
                detailsMovie.imageUrl
            )

        }

        fun toItem(detailsMovieDTO: DetailsMovieDTO): DetailsMovieItem {
            return DetailsMovieItem(
                detailsMovieDTO.id,
                detailsMovieDTO.title,
                detailsMovieDTO.imageUrl
            )
        }
    }
}