package com.example.moviepicker.domain.builder

import com.example.moviepicker.data.dtos.RecommendedMovieDTO
import com.example.moviepicker.domain.items.RecommendedMovieItem

class RecommendedMovieBuilder {
    companion object {
        fun toDTO(recommendedMovieItem: RecommendedMovieItem): RecommendedMovieDTO {
            return RecommendedMovieDTO(
                recommendedMovieItem.id,
                recommendedMovieItem.title,
                recommendedMovieItem.imageUrl,
                recommendedMovieItem.descriptionEn,
                recommendedMovieItem.descriptionRo
            )
        }

        fun toItem(recommendedMovieDTO: RecommendedMovieDTO): RecommendedMovieItem {
            return RecommendedMovieItem(
                recommendedMovieDTO.id,
                recommendedMovieDTO.title,
                recommendedMovieDTO.imageUrl,
                recommendedMovieDTO.descriptionEn,
                recommendedMovieDTO.descriptionRo
            )
        }
    }
}