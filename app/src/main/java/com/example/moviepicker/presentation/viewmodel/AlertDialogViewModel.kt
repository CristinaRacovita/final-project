package com.example.moviepicker.presentation.viewmodel

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.UserDetailsItem
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.activity.GroupActivity
import com.example.moviepicker.presentation.listener.GroupItemListener
import java.util.stream.Collectors

class AlertDialogViewModel(fetchCredentialsUseCase: FetchCredentialsUseCase) : ViewModel(),
    GroupItemListener {
    var credentials: ObservableArrayList<GroupItemViewModel> = ObservableArrayList()
    var filterText: ObservableField<String> = ObservableField()
    var navigationLiveData = MutableLiveData<Class<*>>()
    var groupName: ObservableField<String> = ObservableField()
    var selectedUsers: ObservableArrayList<GroupItemViewModel> = ObservableArrayList()

    init {
        val liveItems: LiveData<List<UserDetailsItem>> = fetchCredentialsUseCase.getCredentials()

        liveItems.observeForever { items: List<UserDetailsItem?>? ->
            if (items != null) {
                val users: MutableList<GroupItemViewModel> = ArrayList()

                for (userItem: UserDetailsItem? in items) {
                    val itemViewModel = GroupItemViewModel()
                    userItem?.id?.let { itemViewModel.userId.set(it) }
                    itemViewModel.username.set(userItem?.email)
                    itemViewModel.isChecked.set(false)
                    if (userItem != null) {
                        itemViewModel.profileImage.set(userItem.profileImage)
                    }
                    users.add(itemViewModel)
                }
                this.credentials.addAll(
                    users
                )
            }

        }
    }

    override fun selectUser(view: View, groupItemViewModel: GroupItemViewModel) {
        groupItemViewModel.isChecked.set(!groupItemViewModel.isChecked.get())
    }

    fun createGroup() {
        val users: List<GroupItemViewModel> =
            credentials.stream().filter { user -> user.isChecked.get() }
                .collect(Collectors.toList())
        selectedUsers.addAll(users)
        navigationLiveData.value = GroupActivity::class.java
    }
}