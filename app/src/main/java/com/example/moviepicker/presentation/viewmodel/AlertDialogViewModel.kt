package com.example.moviepicker.presentation.viewmodel

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.UserItem
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase

class AlertDialogViewModel(fetchCredentialsUseCase: FetchCredentialsUseCase) : ViewModel() {
    var credentials: ObservableArrayList<UserItem> = ObservableArrayList()

    init {
        val liveItems: LiveData<List<UserItem>> = fetchCredentialsUseCase.getCredentials()

        liveItems.observeForever { items: List<UserItem?>? ->
            if (items != null) {
                this.credentials.addAll(
                    items
                )
            }

        }
    }

}