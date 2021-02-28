package com.example.moviepicker.presentation.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.viewmodel.AlertDialogViewModel

class AlertDialogViewModelFactory(
    private val fetchCredentialsUseCase: FetchCredentialsUseCase,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlertDialogViewModel::class.java)) {
            return AlertDialogViewModel(fetchCredentialsUseCase) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}