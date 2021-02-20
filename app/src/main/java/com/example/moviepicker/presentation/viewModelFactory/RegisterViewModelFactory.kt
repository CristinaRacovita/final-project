package com.example.moviepicker.presentation.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.AddUserUseCase
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.viewmodel.RegisterViewModel

class RegisterViewModelFactory(
    private val fetchCredentialsUseCase: FetchCredentialsUseCase,
    private val addUserUseCase: AddUserUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(addUserUseCase, fetchCredentialsUseCase) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}