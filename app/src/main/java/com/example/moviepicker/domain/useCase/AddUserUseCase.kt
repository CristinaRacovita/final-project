package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.items.UserItem
import com.example.moviepicker.domain.mediator.UserMediator

class AddUserUseCase(private val mediator: UserMediator) {
    fun addUser(userItem: UserItem): LiveData<UserItem> {
        return mediator.createNewUser(userItem)
    }
}