package com.example.moviepicker.presentation.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.UserItem
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase

class AlertDialogViewModel(fetchCredentialsUseCase: FetchCredentialsUseCase) : ViewModel() {
    var credentials: ObservableArrayList<GroupItemViewModel> = ObservableArrayList()
    var filterText: ObservableField<String> = ObservableField()

    init {
        val liveItems: LiveData<List<UserItem>> = fetchCredentialsUseCase.getCredentials()

        liveItems.observeForever { items: List<UserItem?>? ->
            if (items != null) {
                val users: MutableList<GroupItemViewModel> = ArrayList()

                for (userItem: UserItem? in items) {
                    val itemViewModel = GroupItemViewModel()
                    itemViewModel.username.set(userItem?.email)
                    users.add(itemViewModel)
                }
                this.credentials.addAll(
                    users
                )
            }

        }
    }
}