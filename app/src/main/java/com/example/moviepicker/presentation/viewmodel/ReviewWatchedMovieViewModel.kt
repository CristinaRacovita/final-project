package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.DetailsMovieItem
import com.example.moviepicker.domain.items.RatingItem
import com.example.moviepicker.domain.useCase.AddRatingsUseCase
import com.example.moviepicker.domain.useCase.FetchMovieDetailsUseCase
import com.example.moviepicker.domain.workers.WatchedWorker
import com.example.moviepicker.presentation.activity.MainActivity

class ReviewWatchedMovieViewModel(
    detailsUseCase: FetchMovieDetailsUseCase,
    private val rateUseCase: AddRatingsUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    var watchedMovie: ObservableField<DetailsMovieItem> = ObservableField()
    var stars: ObservableFloat = ObservableFloat()
    var navigationLiveData = MutableLiveData<Class<*>>()
    private val currentMovieId = sharedPreferences.getInt(WatchedWorker.movieId, -1)

    init {
        val liveItems: LiveData<List<DetailsMovieItem>> =
            detailsUseCase.getPickedMovies("$currentMovieId-")

        liveItems.observeForever { items: List<DetailsMovieItem?>? ->
            if (items != null) {
                val elements: MutableList<DetailsMovieItem> = ArrayList()

                for (movieItem: DetailsMovieItem? in items) {
                    elements.add(movieItem!!)
                }

                this.watchedMovie.set(
                    elements[0]
                )
            }

        }
    }

    fun saveReview() {
        val ratings: MutableList<RatingItem> = ArrayList()

        val currentUserId = sharedPreferences.getInt("id", -1)

        val rating = RatingItem(currentUserId, currentMovieId, stars.get())

        ratings.add(rating)

        rateUseCase.addRatings(ratings)

        navigationLiveData.postValue(MainActivity::class.java)
    }
}