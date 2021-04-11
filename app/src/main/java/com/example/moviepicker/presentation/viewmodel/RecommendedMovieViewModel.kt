package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.RecommendedMovieItem
import com.example.moviepicker.domain.useCase.GetRecommendedMovieUseCase

class RecommendedMovieViewModel(
    val sharedPreferences: SharedPreferences,
    getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
) : ViewModel() {
    var recommendedMovies: ObservableArrayList<RecommendedMovieItem> = ObservableArrayList()

    init {
        val currentUserId = sharedPreferences.getInt("id", -1)
        val liveRecommendation: LiveData<List<RecommendedMovieItem>> =
            getRecommendedMovieUseCase.getRecommended(currentUserId)

        liveRecommendation.observeForever { movies: List<RecommendedMovieItem>? ->
            if (movies != null) {
                recommendedMovies.addAll(movies)
            }

        }
    }
}