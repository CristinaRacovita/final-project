package com.example.moviepicker.domain.useCase

import com.example.moviepicker.domain.UserItem
import com.example.moviepicker.domain.UserMediator

class AddUserUseCase(private val mediator: UserMediator) {
    fun addUser(number: Int, userItem: UserItem){
        mediator.createNewUser(number,userItem)
    }
}