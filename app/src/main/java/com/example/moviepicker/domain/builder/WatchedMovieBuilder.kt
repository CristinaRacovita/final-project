package com.example.moviepicker.domain.builder

import com.example.moviepicker.data.WatchedMovieDTO
import com.example.moviepicker.domain.items.WatchedMovieItem

class WatchedMovieBuilder {
    companion object {
        fun toDTO(watchedMovieItem: WatchedMovieItem): WatchedMovieDTO {
            return WatchedMovieDTO(
                watchedMovieItem.title,
                watchedMovieItem.rating
            )
        }

        fun toItem(watchedMovieDTO: WatchedMovieDTO): WatchedMovieItem {
            return WatchedMovieItem(
                watchedMovieDTO.title,
                watchedMovieDTO.rating
            )
        }
    }
}