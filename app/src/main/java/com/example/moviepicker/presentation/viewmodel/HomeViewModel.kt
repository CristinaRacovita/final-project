package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.useCase.GetRecommendedMovieUseCase

class HomeViewModel(
    val fragmentManager: FragmentManager,
    private val getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
    val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun getRecommendation() {
        val currentUserId = sharedPreferences.getInt("id", -1)
        val recommendation: LiveData<String> =
            getRecommendedMovieUseCase.getRecommended(currentUserId)

        recommendation.observeForever { s: String? ->
            if (s != null) {
                Log.d("RECOMMENDATION MOVIE", s)
            }

        }

    }
}