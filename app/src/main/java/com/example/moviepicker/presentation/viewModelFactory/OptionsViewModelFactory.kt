package com.example.moviepicker.presentation.viewModelFactory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.example.moviepicker.domain.useCase.GetPhotoUseCase
import com.example.moviepicker.presentation.viewmodel.OptionsViewModel

class OptionsViewModelFactory(
    val sharedPreferences: SharedPreferences,
    private val workManager: WorkManager,
    private val getPhotoUseCase: GetPhotoUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OptionsViewModel::class.java)) {
            return OptionsViewModel(sharedPreferences, workManager, getPhotoUseCase) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}