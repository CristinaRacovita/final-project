package com.example.moviepicker.presentation.listener

import com.example.moviepicker.presentation.viewmodel.DisplayMovieItemViewModel

interface DisplayMovieListener {
    fun onItemTap(displayMovieItemViewModel: DisplayMovieItemViewModel)
}