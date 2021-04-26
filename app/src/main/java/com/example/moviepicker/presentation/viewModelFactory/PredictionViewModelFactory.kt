package com.example.moviepicker.presentation.viewModelFactory

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.presentation.viewmodel.PredictionViewModel

class PredictionViewModelFactory(private val fragmentManager: FragmentManager) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PredictionViewModel::class.java)) {
            return PredictionViewModel(fragmentManager) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}