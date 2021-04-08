package com.example.moviepicker.data.remote

import com.example.moviepicker.data.DetailsMovieDTO
import com.example.moviepicker.data.DisplayMovieDTO
import com.example.moviepicker.domain.repository.MovieRepository
import java.util.*

class MovieRemoteDataSource(private val api: MoviePickerAPI) : MovieRepository {
    override fun getMoviesForDisplay(): List<DisplayMovieDTO> {
        val movies = api.getMoviesForDisplay().execute().body()
        if (movies != null) {
            return movies
        }
        return Collections.emptyList()
    }

    override fun getDetailsForPickedMovies(ids: String): List<DetailsMovieDTO> {
        val movies = api.getDetailsForMovies(ids).execute().body()
        if (movies != null) {
            return movies
        }
        return Collections.emptyList()
    }

    override fun getRecommendedMovie(id: Int): String {
        val movieName = api.getRecommendedMovie(id).execute().body()
        if (movieName != null) {
            return movieName
        }

        return "Something gone wrong."
    }
}