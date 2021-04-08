package com.example.moviepicker.presentation.viewmodel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class PickedMovieItemViewModel {
    var movieTitle = ObservableField<String>()
    var movieId = ObservableInt()
    var rating = ObservableInt()
    var imageUrl = ObservableField<String>()
}