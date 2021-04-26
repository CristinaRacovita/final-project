package com.example.moviepicker.presentation.viewModelFactory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.example.moviepicker.domain.useCase.GetRecommendedMovieUseCase
import com.example.moviepicker.presentation.viewmodel.RecommendedMovieViewModel

class RecommendedMovieViewModelFactory(
    val sharedPreferences: SharedPreferences,
    private val getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
    private val workManager: WorkManager,
    private val genre: String?,
    private val year: String?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecommendedMovieViewModel::class.java)) {
            return RecommendedMovieViewModel(
                sharedPreferences,
                getRecommendedMovieUseCase,
                workManager,
                genre,
                year
            ) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}