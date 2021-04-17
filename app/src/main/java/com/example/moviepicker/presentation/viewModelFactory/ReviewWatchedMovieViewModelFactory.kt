package com.example.moviepicker.presentation.viewModelFactory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.AddRatingsUseCase
import com.example.moviepicker.domain.useCase.FetchMovieDetailsUseCase
import com.example.moviepicker.presentation.viewmodel.ReviewWatchedMovieViewModel

class ReviewWatchedMovieViewModelFactory(
    private val detailsUseCase: FetchMovieDetailsUseCase,
    private val ratingsUseCase: AddRatingsUseCase,
    val sharedPreferences: SharedPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReviewWatchedMovieViewModel::class.java)) {
            return ReviewWatchedMovieViewModel(
                detailsUseCase,
                ratingsUseCase,
                sharedPreferences
            ) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}