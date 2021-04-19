package com.example.moviepicker.domain.repository

import com.example.moviepicker.data.DetailsMovieDTO
import com.example.moviepicker.data.DisplayMovieDTO
import com.example.moviepicker.data.RecommendedMovieDTO
import com.example.moviepicker.data.WatchedMovieDTO

interface MovieRepository {
    fun getMoviesForDisplay(): List<DisplayMovieDTO>
    fun getDetailsForPickedMovies(ids: String): List<DetailsMovieDTO>
    fun getRecommendedMovie(id: Int): List<RecommendedMovieDTO>
    fun getWatchedMovies(id: Int): List<WatchedMovieDTO>
}