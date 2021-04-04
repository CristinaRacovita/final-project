package com.example.moviepicker.data.remote

import com.example.moviepicker.data.DisplayMovieDTO
import com.example.moviepicker.domain.repository.DisplayMovieRepository
import java.util.*

class DisplayMovieRemoteDataSource(private val api: MoviePickerAPI) : DisplayMovieRepository {
    override fun getMoviesForDisplay(): List<DisplayMovieDTO> {
        val movies = api.getMoviesForDisplay().execute().body()
        if (movies != null) {
            return movies
        }
        return Collections.emptyList()
    }
}