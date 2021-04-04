package com.example.moviepicker.presentation.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.useCase.FetchDisplayMovieUseCase
import com.example.moviepicker.presentation.listener.DisplayMovieListener

class ChooseMoviesViewModel(fetchDisplayMovieUseCase: FetchDisplayMovieUseCase) : ViewModel(),
    DisplayMovieListener {
    var movies: ObservableArrayList<DisplayMovieItemViewModel> = ObservableArrayList()
    var numbersOfPickedMovies = ObservableInt()

    private var pickedMovies: MutableList<DisplayMovieItemViewModel> = ArrayList()
    var filterText: ObservableField<String> = ObservableField()

    init {
        val liveItems: LiveData<List<DisplayMovieItem>> = fetchDisplayMovieUseCase.getMovies()

        liveItems.observeForever { items: List<DisplayMovieItem?>? ->
            if (items != null) {
                val movies: MutableList<DisplayMovieItemViewModel> = ArrayList()

                for (movieItem: DisplayMovieItem? in items) {
                    val movieItemViewModel = DisplayMovieItemViewModel()
                    movieItemViewModel.movieTitle.set(movieItem?.title)
                    movieItem?.id?.let { movieItemViewModel.movieId.set(it) }
                    movies.add(movieItemViewModel)
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
            if(displayMovieItemViewModel.isMoviePicked.get()){
                numbersOfPickedMovies.set(numbersOfPickedMovies.get() + 1)
            }else{
                numbersOfPickedMovies.set(numbersOfPickedMovies.get() - 1)
            }
        }
    }
}