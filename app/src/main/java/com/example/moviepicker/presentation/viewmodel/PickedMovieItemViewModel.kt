package com.example.moviepicker.presentation.viewmodel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt

class PickedMovieItemViewModel {
    var movieTitle = ObservableField<String>()
    var movieId = ObservableInt()
    var rating = ObservableFloat()
    var imageUrl = ObservableField<String>()
}