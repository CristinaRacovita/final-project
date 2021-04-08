package com.example.moviepicker.presentation.listener

import android.view.View
import com.example.moviepicker.presentation.viewmodel.PickedMovieItemViewModel

interface RateListener {
    fun rateMovie(ratingBar: View, pickedMovieItemViewModel: PickedMovieItemViewModel)
}