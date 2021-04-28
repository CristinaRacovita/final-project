package com.example.moviepicker.presentation.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.FetchUserDetailsUseCase
import com.example.moviepicker.presentation.viewmodel.GroupViewModel

class GroupViewModelFactory(
    val title: String,
    private val fetchUserDetailsUseCase: FetchUserDetailsUseCase,
    private val flag: Boolean,
    private val users: List<Any>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupViewModel::class.java)) {
            return GroupViewModel(title, fetchUserDetailsUseCase, flag, users) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}