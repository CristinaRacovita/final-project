package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.RatingBar
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.R
import com.example.moviepicker.domain.items.DetailsMovieItem
import com.example.moviepicker.domain.items.RatingItem
import com.example.moviepicker.domain.useCase.AddRatingsUseCase
import com.example.moviepicker.domain.useCase.FetchMovieDetailsUseCase
import com.example.moviepicker.presentation.activity.MainActivity
import com.example.moviepicker.presentation.listener.RateListener

class RateMoviesViewModel(
    detailsUseCase: FetchMovieDetailsUseCase,
    private val rateUseCase: AddRatingsUseCase,
    val sharedPreferences: SharedPreferences,
    movies: List<DisplayMovieItemViewModel>
) : ViewModel(), RateListener {
    var pickedMovies: ObservableArrayList<PickedMovieItemViewModel> = ObservableArrayList()
    var navigationLiveData = MutableLiveData<Class<*>>()

    init {
        var ids = ""

        for (movie in movies) {
            ids += movie.movieId.get().toString() + "-"
        }
        val liveItems: LiveData<List<DetailsMovieItem>> = detailsUseCase.getPickedMovies(ids)

        liveItems.observeForever { items: List<DetailsMovieItem?>? ->
            if (items != null) {
                val elements: MutableList<PickedMovieItemViewModel> = ArrayList()

                for (movieItem: DetailsMovieItem? in items) {
                    val movieItemViewModel = PickedMovieItemViewModel()
                    movieItemViewModel.movieTitle.set(movieItem?.title)
                    movieItemViewModel.imageUrl.set(movieItem?.imageUrl)
                    movieItem?.id?.let { movieItemViewModel.movieId.set(it) }
                    elements.add(movieItemViewModel)
                }
                this.pickedMovies.addAll(
                    elements
                )
            }

        }
    }

    fun goMainPage() {
        val ratings: MutableList<RatingItem> = ArrayList()
        val currentUserId = sharedPreferences.getInt("id", -1)

        for (movie in pickedMovies) {
            ratings.add(RatingItem(currentUserId, movie.movieId.get(), movie.rating.get()))
        }

        rateUseCase.addRatings(ratings)

        navigationLiveData.value = MainActivity::class.java
    }

    override fun rateMovie(
        ratingBar: View,
        pickedMovieItemViewModel: PickedMovieItemViewModel
    ) {
        ratingBar as RatingBar
        val stars: Int = ratingBar.rating.toInt()
        pickedMovieItemViewModel.rating.set(stars)
    }
}