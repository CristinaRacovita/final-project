package com.example.moviepicker.domain.useCase

import com.example.moviepicker.domain.items.UserItem
import com.example.moviepicker.domain.mediator.UserMediator

class AddUserUseCase(private val mediator: UserMediator) {
    fun addUser(userItem: UserItem){
        mediator.createNewUser(userItem)
    }
}