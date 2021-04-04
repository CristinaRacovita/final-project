package com.example.moviepicker.domain.mediator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviepicker.domain.builder.DisplayMovieBuilder
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.repository.DisplayMovieRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.stream.Collectors

class DisplayMovieMediator(private val remoteRepository: DisplayMovieRepository) {
    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private var movies: MutableLiveData<List<DisplayMovieItem>> = MutableLiveData()

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
}