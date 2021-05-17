package com.example.moviepicker.presentation.viewModelFactory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.FetchGroupsUseCase
import com.example.moviepicker.presentation.viewmodel.AllGroupsViewModel

class AllGroupsViewModelFactory(
    private val sharedPreferences: SharedPreferences,
    private val fetchGroupsUseCase: FetchGroupsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllGroupsViewModel::class.java)) {
            return AllGroupsViewModel(sharedPreferences, fetchGroupsUseCase) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}