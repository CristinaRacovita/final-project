package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.items.AllGroupsItem
import com.example.moviepicker.domain.mediator.GroupMediator

class FetchGroupsUseCase(private val mediator: GroupMediator) {
    fun getGroups(id: Int): LiveData<List<AllGroupsItem>> {
        return mediator.getGroups(id)
    }
}