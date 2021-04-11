package com.example.moviepicker.presentation.viewModelFactory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.GetRecommendedMovieUseCase
import com.example.moviepicker.presentation.viewmodel.RecommendedMovieViewModel

class RecommendedMovieViewModelFactory(
    val sharedPreferences: SharedPreferences,
    private val getRecommendedMovieUseCase: GetRecommendedMovieUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecommendedMovieViewModel::class.java)) {
            return RecommendedMovieViewModel(sharedPreferences, getRecommendedMovieUseCase) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}