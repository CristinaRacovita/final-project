package com.example.moviepicker.domain.repository

import com.example.moviepicker.data.DetailsMovieDTO
import com.example.moviepicker.data.DisplayMovieDTO

interface MovieRepository {
    fun getMoviesForDisplay(): List<DisplayMovieDTO>
    fun getDetailsForPickedMovies(ids: String): List<DetailsMovieDTO>
    fun getRecommendedMovie(id: Int): String
}