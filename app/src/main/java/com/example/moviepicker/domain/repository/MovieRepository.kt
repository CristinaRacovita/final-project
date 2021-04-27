package com.example.moviepicker.domain.repository

import com.example.moviepicker.data.dtos.DetailsMovieDTO
import com.example.moviepicker.data.dtos.DisplayMovieDTO
import com.example.moviepicker.data.dtos.RecommendedMovieDTO
import com.example.moviepicker.data.dtos.WatchedMovieDTO

interface MovieRepository {
    fun getMoviesForDisplay(): List<DisplayMovieDTO>
    fun getDetailsForPickedMovies(ids: String): List<DetailsMovieDTO>
    fun getRecommendedMovie(id: Int, genre: String?, year: String?): List<RecommendedMovieDTO>
    fun getWatchedMovies(id: Int): List<WatchedMovieDTO>
}