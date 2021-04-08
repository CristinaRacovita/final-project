package com.example.moviepicker.presentation.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import java.io.Serializable

class DisplayMovieItemViewModel : ViewModel(), Serializable {
    var movieTitle = ObservableField<String>()
    var movieId = ObservableInt()
    var isMoviePicked = ObservableBoolean()
}