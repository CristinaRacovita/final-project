package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.items.UserDetailsItem
import com.example.moviepicker.domain.mediator.UserMediator

class FetchUserDetailsUseCase(private val mediator: UserMediator) {
    fun getUsersDetails(ids: String): LiveData<List<UserDetailsItem>> {
        return mediator.getUsersDetails(ids)
    }
}