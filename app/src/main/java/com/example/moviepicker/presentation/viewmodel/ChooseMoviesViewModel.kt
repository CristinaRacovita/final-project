package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.items.WatchedMovieItem
import com.example.moviepicker.domain.useCase.FetchDisplayMovieUseCase
import com.example.moviepicker.domain.useCase.FetchWatchedMoviesUseCase
import com.example.moviepicker.presentation.activity.RateMoviesActivity
import com.example.moviepicker.presentation.listener.DisplayMovieListener

class ChooseMoviesViewModel(
    val isWatched: Boolean,
    fetchDisplayMovieUseCase: FetchDisplayMovieUseCase,
    val sharedPreferences: SharedPreferences,
    fetchWatchedMoviesUseCase: FetchWatchedMoviesUseCase
) : ViewModel(),
    DisplayMovieListener {
    var movies: ObservableArrayList<DisplayMovieItemViewModel> = ObservableArrayList()
    var numbersOfPickedMovies = ObservableInt()

    var pickedMovies: ArrayList<DisplayMovieItemViewModel> = ArrayList()
    var filterText: ObservableField<String> = ObservableField()
    var navigationLiveData = MutableLiveData<Class<*>>()

    var watchedMovies: ObservableArrayList<WatchedMovieItem> = ObservableArrayList()


    init {
        if (isWatched) {
            val currentUserId = sharedPreferences.getInt("id", -1)

            val watchedItems: LiveData<List<WatchedMovieItem>> =
                fetchWatchedMoviesUseCase.getWatchedMovies(currentUserId)

            watchedItems.observeForever { items: List<WatchedMovieItem?>? ->
                if (items != null) {
                    this.watchedMovies.addAll(
                        items
                    )
                }

            }
        }

        val liveItems: LiveData<List<DisplayMovieItem>> = fetchDisplayMovieUseCase.getMovies()

        liveItems.observeForever { items: List<DisplayMovieItem?>? ->
            if (items != null) {
                val movies: MutableList<DisplayMovieItemViewModel> = ArrayList()

                for (movieItem: DisplayMovieItem? in items) {
                    if (isWatched) {
                        if (!checkIfWatchedMovie(movieItem!!)) {
                            val movieItemViewModel = DisplayMovieItemViewModel()
                            movieItemViewModel.movieTitle.set(movieItem.title)
                            movieItem.id.let { movieItemViewModel.movieId.set(it) }
                            movies.add(movieItemViewModel)
                        }
                    } else {
                        val movieItemViewModel = DisplayMovieItemViewModel()
                        movieItemViewModel.movieTitle.set(movieItem?.title)
                        movieItem?.id?.let { movieItemViewModel.movieId.set(it) }
                        movies.add(movieItemViewModel)
                    }
                }
                this.movies.addAll(
                    movies
                )
            }

        }
    }

    override fun onItemTap(displayMovieItemViewModel: DisplayMovieItemViewModel) {
        if((numbersOfPickedMovies.get() == 5 && displayMovieItemViewModel.isMoviePicked.get()) || numbersOfPickedMovies.get() < 5){
            displayMovieItemViewModel.isMoviePicked.set(!displayMovieItemViewModel.isMoviePicked.get())
            if (displayMovieItemViewModel.isMoviePicked.get()) {
                numbersOfPickedMovies.set(numbersOfPickedMovies.get() + 1)
                pickedMovies.add(displayMovieItemViewModel)
            } else {
                numbersOfPickedMovies.set(numbersOfPickedMovies.get() - 1)
                pickedMovies.removeIf { d -> d.movieId.get() == displayMovieItemViewModel.movieId.get() }
            }
        }
    }

    fun addReviews() {
        navigationLiveData.value = RateMoviesActivity::class.java
    }

    private fun checkIfWatchedMovie(displayMovieItem: DisplayMovieItem): Boolean {
        for (watchedMovie in watchedMovies) {
            if (watchedMovie.title == displayMovieItem.title) {
                return true
            }
        }

        return false
    }
}