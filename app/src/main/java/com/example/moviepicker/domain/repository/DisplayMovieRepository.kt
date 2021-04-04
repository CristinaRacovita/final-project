package com.example.moviepicker.domain.repository

import com.example.moviepicker.data.DisplayMovieDTO

interface DisplayMovieRepository {
    fun getMoviesForDisplay(): List<DisplayMovieDTO>
}