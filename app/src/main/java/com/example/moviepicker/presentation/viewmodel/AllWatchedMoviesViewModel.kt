package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.WatchedMovieItem
import com.example.moviepicker.domain.useCase.FetchWatchedMoviesUseCase
import com.example.moviepicker.presentation.activity.ChooseMoviesActivity

class AllWatchedMoviesViewModel(
    fetchWatchedMoviesUseCase: FetchWatchedMoviesUseCase, val sharedPreferences: SharedPreferences,
) : ViewModel() {
    var watchedMovies: ObservableArrayList<WatchedMovieItem> = ObservableArrayList()
    var navigationLiveData = MutableLiveData<Class<*>>()

    init {
        val currentUserId = sharedPreferences.getInt("id", -1)

        val liveItems: LiveData<List<WatchedMovieItem>> =
            fetchWatchedMoviesUseCase.getWatchedMovies(currentUserId)

        liveItems.observeForever { items: List<WatchedMovieItem?>? ->
            if (items != null) {
                this.watchedMovies.addAll(
                    items
                )
            }

        }
    }

    fun goAddMoviesPage() {
        navigationLiveData.value = ChooseMoviesActivity::class.java
    }
}