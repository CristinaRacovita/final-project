package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.UserItem
import com.example.moviepicker.domain.UserMediator

class FetchCredentialsUseCase(private val mediator: UserMediator) {
    fun getCredentials(): LiveData<List<UserItem>> {
        return mediator.getCredentials()
    }
}