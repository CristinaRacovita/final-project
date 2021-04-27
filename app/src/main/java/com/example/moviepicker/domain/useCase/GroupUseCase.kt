package com.example.moviepicker.domain.useCase

import androidx.lifecycle.MutableLiveData
import com.example.moviepicker.domain.items.GroupItem
import com.example.moviepicker.domain.items.GroupUserItem
import com.example.moviepicker.domain.mediator.GroupMediator

class GroupUseCase(private val mediator: GroupMediator) {
    fun createGroup(group: GroupItem): MutableLiveData<GroupItem> {
        return mediator.createGroup(group)
    }

    fun addMembers(groupUsers: List<GroupUserItem>) {
        mediator.addMembers(groupUsers)
    }
}