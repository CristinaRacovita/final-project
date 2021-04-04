package com.example.moviepicker.presentation.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.FetchDisplayMovieUseCase
import com.example.moviepicker.presentation.viewmodel.ChooseMoviesViewModel

class ChooseViewModelFactory(private val fetchDisplayMovieUseCase: FetchDisplayMovieUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChooseMoviesViewModel::class.java)) {
            return ChooseMoviesViewModel(fetchDisplayMovieUseCase) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}