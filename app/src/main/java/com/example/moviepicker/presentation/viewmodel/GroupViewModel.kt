package com.example.moviepicker.presentation.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.UserDetailsItem
import com.example.moviepicker.domain.useCase.FetchUserDetailsUseCase
import com.example.moviepicker.presentation.activity.MainActivity

class GroupViewModel(
    val title: String,
    fetchUserDetailsUseCase: FetchUserDetailsUseCase,
    ids: String
) :
    ViewModel() {
    var users: ObservableArrayList<UserDetailsItem> = ObservableArrayList()
    var navigationLiveData = MutableLiveData<Class<*>>()

    init {
        val usersLive: LiveData<List<UserDetailsItem>> =
            fetchUserDetailsUseCase.getUsersDetails(ids)

        usersLive.observeForever { items: List<UserDetailsItem?>? ->
            if (items != null) {
                this.users.addAll(
                    items
                )
            }

        }
    }

    fun getRecommendation() {
        navigationLiveData.value = MainActivity::class.java
    }

}