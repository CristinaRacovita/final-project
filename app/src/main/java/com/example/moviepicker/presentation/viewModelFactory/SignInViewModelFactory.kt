package com.example.moviepicker.presentation.viewModelFactory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.domain.useCase.FetchUnratedMovies
import com.example.moviepicker.presentation.viewmodel.SignInViewModel

class SignInViewModelFactory(
    private val fetchCredentialsUseCase: FetchCredentialsUseCase,
    private val sharedPreferences: SharedPreferences,
    private val fetchUnratedMovies: FetchUnratedMovies,
    private val workManager: WorkManager
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(
                fetchCredentialsUseCase, sharedPreferences,
                fetchUnratedMovies, workManager
            ) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}