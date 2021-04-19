package com.example.moviepicker.presentation.viewModelFactory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.AddRatingsUseCase
import com.example.moviepicker.domain.useCase.FetchMovieDetailsUseCase
import com.example.moviepicker.domain.useCase.FetchWatchedMoviesUseCase
import com.example.moviepicker.presentation.viewmodel.DisplayMovieItemViewModel
import com.example.moviepicker.presentation.viewmodel.RateMoviesViewModel

class RateMoviesViewModelFactory(
    private val detailsUseCase: FetchMovieDetailsUseCase,
    private val ratingsUseCase: AddRatingsUseCase,
    val sharedPreferences: SharedPreferences,
    val movies: List<DisplayMovieItemViewModel>,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RateMoviesViewModel::class.java)) {
            return RateMoviesViewModel(
                detailsUseCase,
                ratingsUseCase,
                sharedPreferences,
                movies,
            ) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}