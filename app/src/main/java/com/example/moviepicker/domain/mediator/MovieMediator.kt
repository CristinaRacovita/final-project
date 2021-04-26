package com.example.moviepicker.domain.mediator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviepicker.domain.builder.DetailsMovieBuilder
import com.example.moviepicker.domain.builder.DisplayMovieBuilder
import com.example.moviepicker.domain.builder.RecommendedMovieBuilder
import com.example.moviepicker.domain.builder.WatchedMovieBuilder
import com.example.moviepicker.domain.items.DetailsMovieItem
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.items.RecommendedMovieItem
import com.example.moviepicker.domain.items.WatchedMovieItem
import com.example.moviepicker.domain.repository.MovieRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.stream.Collectors

class MovieMediator(private val remoteRepository: MovieRepository) {
    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private var movies: MutableLiveData<List<DisplayMovieItem>> = MutableLiveData()
    private var pickedMovies: MutableLiveData<List<DetailsMovieItem>> = MutableLiveData()
    private var recommendedMovies: MutableLiveData<List<RecommendedMovieItem>> = MutableLiveData()
    private var watchedMovies: MutableLiveData<List<WatchedMovieItem>> = MutableLiveData()

    fun getMovies(): LiveData<List<DisplayMovieItem>> {
        executorService.execute {
            movies.postValue(
                remoteRepository.getMoviesForDisplay().stream().map(DisplayMovieBuilder::toItem)
                    .collect(
                        Collectors.toList()
                    )
            )
        }

        return movies
    }

    fun getDetailsForPickedMovies(ids: String): LiveData<List<DetailsMovieItem>> {
        executorService.execute {
            pickedMovies.postValue(
                remoteRepository.getDetailsForPickedMovies(ids).stream()
                    .map(DetailsMovieBuilder::toItem)
                    .collect(
                        Collectors.toList()
                    )
            )
        }

        return pickedMovies
    }

    fun getRecommendedMovie(
        id: Int,
        genre: String,
        year: String
    ): MutableLiveData<List<RecommendedMovieItem>> {
        executorService.execute {
            recommendedMovies.postValue(
                remoteRepository.getRecommendedMovie(id, genre, year).stream()
                    .map(RecommendedMovieBuilder::toItem).collect(Collectors.toList())
            )
        }

        return recommendedMovies
    }

    fun getWatchedMovies(id: Int): MutableLiveData<List<WatchedMovieItem>> {
        executorService.execute {
            watchedMovies.postValue(
                remoteRepository.getWatchedMovies(id).stream()
                    .map(WatchedMovieBuilder::toItem).collect(Collectors.toList())
            )
        }

        return watchedMovies
    }
}