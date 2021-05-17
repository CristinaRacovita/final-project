package com.example.moviepicker.presentation.viewModelFactory

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.domain.useCase.FetchDisplayMovieUseCase
import com.example.moviepicker.domain.useCase.FetchUserDetailsUseCase
import com.example.moviepicker.presentation.viewmodel.GroupViewModel

class GroupViewModelFactory(
    val title: String,
    private val fetchUserDetailsUseCase: FetchUserDetailsUseCase,
    private val flag: Boolean,
    private val users: List<Any>,
    private val fragmentManager: FragmentManager,
    private val groupId: Int,
    private val fetchDisplayMovieUseCase: FetchDisplayMovieUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupViewModel::class.java)) {
            return GroupViewModel(
                title,
                fetchUserDetailsUseCase,
                flag,
                users,
                fragmentManager,
                groupId,
                fetchDisplayMovieUseCase
            ) as T
        }

        throw IllegalArgumentException("Unable to construct view model")
    }
}