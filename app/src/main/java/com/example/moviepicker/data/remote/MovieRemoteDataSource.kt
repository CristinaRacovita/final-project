package com.example.moviepicker.data.remote

import com.example.moviepicker.data.dtos.DetailsMovieDTO
import com.example.moviepicker.data.dtos.DisplayMovieDTO
import com.example.moviepicker.data.dtos.RecommendedMovieDTO
import com.example.moviepicker.data.dtos.WatchedMovieDTO
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

    override fun getRecommendedMovie(
        id: Int,
        genre: String?,
        year: String?
    ): List<RecommendedMovieDTO> {
        var myGenre = genre
        if (myGenre == null) {
            myGenre = ""
        }

        var myYear = year
        if (myYear == null) {
            myYear = ""
        }

        val movies = api.getRecommendedMovie(id, myGenre, myYear).execute().body()
        if (movies != null) {
            return movies
        }

        return Collections.emptyList()
    }

    override fun getWatchedMovies(id: Int): List<WatchedMovieDTO> {
        val movies = api.getWatchedMovies(id).execute().body()
        if (movies != null) {
            return movies
        }

        return Collections.emptyList()
    }

    override fun getGroupRecommendedMovies(ids: String): List<RecommendedMovieDTO> {
        val movies = api.getGroupRecommendedMovies(ids).execute().body()
        if (movies != null) {
            return movies
        }

        return Collections.emptyList()
    }

    override fun getUnratedMovies(id: Int): List<DisplayMovieDTO> {
        val movies = api.getUnratedMovies(id).execute().body()
        if (movies != null) {
            return movies
        }

        return Collections.emptyList()
    }

    override fun getGroupMovies(id: Int): List<DisplayMovieDTO> {
        val movies = api.getGroupMovies(id).execute().body()
        if (movies != null) {
            return movies
        }

        return Collections.emptyList()
    }
}