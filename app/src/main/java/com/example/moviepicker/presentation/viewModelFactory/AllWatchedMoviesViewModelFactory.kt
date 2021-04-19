package com.example.moviepicker.presentation.viewModelFactory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.FetchWatchedMoviesUseCase
import com.example.moviepicker.presentation.viewmodel.AllWatchedMoviesViewModel

class AllWatchedMoviesViewModelFactory(
    private val fetchWatchedMoviesUseCase: FetchWatchedMoviesUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllWatchedMoviesViewModel::class.java)) {
            return AllWatchedMoviesViewModel(fetchWatchedMoviesUseCase, sharedPreferences) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}