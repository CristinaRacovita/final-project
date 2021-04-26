package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.moviepicker.domain.items.RecommendedMovieItem
import com.example.moviepicker.domain.useCase.GetRecommendedMovieUseCase
import com.example.moviepicker.domain.workers.WatchedWorker
import com.example.moviepicker.presentation.listener.WatchedMovieListener
import java.util.concurrent.TimeUnit

class RecommendedMovieViewModel(
    val sharedPreferences: SharedPreferences,
    getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
    private val workManager: WorkManager,
    genre: String?,
    year: String?
) : ViewModel(), WatchedMovieListener {
    var recommendedMovies: ObservableArrayList<RecommendedMovieItem> = ObservableArrayList()
    var closedLiveData = MutableLiveData<Boolean>()

    init {
        val currentUserId = sharedPreferences.getInt("id", -1)
        val liveRecommendation: LiveData<List<RecommendedMovieItem>> =
            getRecommendedMovieUseCase.getRecommended(currentUserId, genre, year)

        liveRecommendation.observeForever { movies: List<RecommendedMovieItem>? ->
            if (movies != null) {
                recommendedMovies.addAll(movies)
            }

        }
    }

    override fun watchedMovie(recommendedMovieItem: RecommendedMovieItem) {
        val data: Data = workDataOf(
            WatchedWorker.movieTitle to recommendedMovieItem.title,
            WatchedWorker.movieId to recommendedMovieItem.id
        )

        val watchedPeriodicRequest =
            OneTimeWorkRequest.Builder(WatchedWorker::class.java)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setInputData(data)
                .build()

        workManager.enqueue(
            watchedPeriodicRequest
        )

        closedLiveData.value = true
    }
}