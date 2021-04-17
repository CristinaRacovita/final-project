package com.example.moviepicker.presentation.listener

import com.example.moviepicker.domain.items.RecommendedMovieItem

interface WatchedMovieListener {
    fun watchedMovie(recommendedMovieItem: RecommendedMovieItem)
}