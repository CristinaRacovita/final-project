package com.example.moviepicker.presentation.viewModelFactory

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.presentation.viewmodel.HomeViewModel

class HomeViewModelFactory(
    private val fragmentManager: FragmentManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                fragmentManager
            ) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}