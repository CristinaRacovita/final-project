package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.GroupItem
import com.example.moviepicker.domain.useCase.FetchGroupsUseCase

class AllGroupsViewModel(
    sharedPreferences: SharedPreferences,
    fetchGroupsUseCase: FetchGroupsUseCase
) : ViewModel() {
    var groups: ObservableArrayList<GroupItem> = ObservableArrayList()

    init {
        val currentUserId = sharedPreferences.getInt("id", -1)

        val groupsLive: LiveData<List<GroupItem>> =
            fetchGroupsUseCase.getGroups(currentUserId)

        groupsLive.observeForever { groupsItems: List<GroupItem>? ->
            if (groupsItems != null) {
                groups.addAll(
                    groupsItems
                )
            }
        }
    }
}