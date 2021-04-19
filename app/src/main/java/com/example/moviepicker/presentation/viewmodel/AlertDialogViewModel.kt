package com.example.moviepicker.presentation.viewmodel

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.R
import com.example.moviepicker.domain.items.UserItem
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.activity.GroupActivity
import com.example.moviepicker.presentation.listener.GroupItemListener

class AlertDialogViewModel(fetchCredentialsUseCase: FetchCredentialsUseCase) : ViewModel(),
    GroupItemListener {
    var credentials: ObservableArrayList<GroupItemViewModel> = ObservableArrayList()
    var filterText: ObservableField<String> = ObservableField()
    var navigationLiveData = MutableLiveData<Class<*>>()
    var groupName: ObservableField<String> = ObservableField()

    init {
        val liveItems: LiveData<List<UserItem>> = fetchCredentialsUseCase.getCredentials()

        liveItems.observeForever { items: List<UserItem?>? ->
            if (items != null) {
                val users: MutableList<GroupItemViewModel> = ArrayList()

                for (userItem: UserItem? in items) {
                    val itemViewModel = GroupItemViewModel()
                    userItem?.id?.let { itemViewModel.userId.set(it) }
                    itemViewModel.username.set(userItem?.email)
                    itemViewModel.isChecked.set(false)
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
        if (groupItemViewModel.isChecked.get()) {
            view.setBackgroundColor(view.context.getColor(R.color.red))
        } else {
            view.setBackgroundColor(view.context.getColor(R.color.grey))
        }
    }

    fun createGroup() {
        navigationLiveData.value = GroupActivity::class.java
    }
}