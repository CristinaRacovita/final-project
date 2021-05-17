package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.AllGroupsItem
import com.example.moviepicker.domain.items.UserDetailsItem
import com.example.moviepicker.domain.useCase.FetchGroupsUseCase
import com.example.moviepicker.presentation.activity.GroupActivity
import com.example.moviepicker.presentation.listener.GroupListener

class AllGroupsViewModel(
    sharedPreferences: SharedPreferences,
    fetchGroupsUseCase: FetchGroupsUseCase
) : ViewModel(), GroupListener {
    var groups: ObservableArrayList<AllGroupsItem> = ObservableArrayList()
    var navigationLiveData = MutableLiveData<Class<*>>()
    var selectedGroupName = MutableLiveData<String>()
    var selectedGroupUsers = MutableLiveData<ArrayList<UserDetailsItem>>()
    var groupId = MutableLiveData<Int>()

    init {
        val currentUserId = sharedPreferences.getInt("id", -1)

        val groupsLive: LiveData<List<AllGroupsItem>> =
            fetchGroupsUseCase.getGroups(currentUserId)

        groupsLive.observeForever { groupsItems: List<AllGroupsItem>? ->
            if (groupsItems != null) {
                groups.addAll(
                    groupsItems
                )
            }
        }

        groups
    }

    override fun goToGroup(allGroupsItem: AllGroupsItem) {
        selectedGroupName.value = allGroupsItem.name
        selectedGroupUsers.value = allGroupsItem.users as ArrayList
        groupId.value = allGroupsItem.id!!
        navigationLiveData.value = GroupActivity::class.java
    }
}