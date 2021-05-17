package com.example.moviepicker.presentation.viewModelFactory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.domain.useCase.GroupUseCase
import com.example.moviepicker.presentation.viewmodel.AlertDialogViewModel

class AlertDialogViewModelFactory(
    private val fetchCredentialsUseCase: FetchCredentialsUseCase,
    private val groupUseCase: GroupUseCase,
    private val sharedPreferences: SharedPreferences
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlertDialogViewModel::class.java)) {
            return AlertDialogViewModel(
                fetchCredentialsUseCase,
                groupUseCase,
                sharedPreferences
            ) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}