package com.example.moviepicker.domain.mediator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviepicker.domain.builder.DetailsMovieBuilder
import com.example.moviepicker.domain.builder.DisplayMovieBuilder
import com.example.moviepicker.domain.items.DetailsMovieItem
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.repository.MovieRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.stream.Collectors

class MovieMediator(private val remoteRepository: MovieRepository) {
    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private var movies: MutableLiveData<List<DisplayMovieItem>> = MutableLiveData()
    private var pickedMovies: MutableLiveData<List<DetailsMovieItem>> = MutableLiveData()
    private var recommendedMovie: MutableLiveData<String> = MutableLiveData()


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

    fun getRecommendedMovie(id: Int): MutableLiveData<String> {
        executorService.execute {
            recommendedMovie.postValue(
                remoteRepository.getRecommendedMovie(id)
            )
        }

        return recommendedMovie
    }
}