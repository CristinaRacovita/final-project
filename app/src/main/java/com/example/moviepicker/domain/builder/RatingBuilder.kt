package com.example.moviepicker.domain.builder

import com.example.moviepicker.data.RatingDTO
import com.example.moviepicker.domain.items.RatingItem

class RatingBuilder {
    companion object {
        fun toDTO(ratingItem: RatingItem): RatingDTO {
            return RatingDTO(ratingItem.userId, ratingItem.movieId, ratingItem.rating.toFloat())
        }

        fun toItem(ratingDTO: RatingDTO): RatingItem {
            return RatingItem(ratingDTO.userId, ratingDTO.movieId, ratingDTO.rating.toInt())
        }
    }
}