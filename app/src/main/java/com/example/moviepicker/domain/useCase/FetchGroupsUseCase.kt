package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.items.GroupItem
import com.example.moviepicker.domain.mediator.GroupMediator

class FetchGroupsUseCase(private val mediator: GroupMediator) {
    fun getGroups(id: Int): LiveData<List<GroupItem>> {
        return mediator.getGroups(id)
    }
}