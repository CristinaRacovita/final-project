package com.example.moviepicker.presentation.viewModelFactory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.FetchDisplayMovieUseCase
import com.example.moviepicker.domain.useCase.FetchWatchedMoviesUseCase
import com.example.moviepicker.presentation.viewmodel.ChooseMoviesViewModel

class ChooseViewModelFactory(
    private val isWatched: Boolean, private val fetchDisplayMovieUseCase: FetchDisplayMovieUseCase,
    val sharedPreferences: SharedPreferences,
    private val fetchWatchedMoviesUseCase: FetchWatchedMoviesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChooseMoviesViewModel::class.java)) {
            return ChooseMoviesViewModel(
                isWatched,
                fetchDisplayMovieUseCase,
                sharedPreferences,
                fetchWatchedMoviesUseCase
            ) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}