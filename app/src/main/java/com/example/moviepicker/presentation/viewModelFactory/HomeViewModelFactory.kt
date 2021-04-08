package com.example.moviepicker.presentation.viewModelFactory

import android.content.SharedPreferences
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.GetRecommendedMovieUseCase
import com.example.moviepicker.presentation.viewmodel.HomeViewModel

class HomeViewModelFactory(
    private val fragmentManager: FragmentManager,
    private val getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
    val sharedPreferences: SharedPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                fragmentManager,
                getRecommendedMovieUseCase,
                sharedPreferences
            ) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}