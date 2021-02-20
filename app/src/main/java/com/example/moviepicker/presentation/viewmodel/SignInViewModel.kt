package com.example.moviepicker.presentation.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.UserItem
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.activity.MainActivity
import com.example.moviepicker.presentation.activity.RegisterActivity


class SignInViewModel(
    fetchCredentialsUseCase: FetchCredentialsUseCase
) : ViewModel() {
    var liveEmail: ObservableField<String> = ObservableField()
    var livePassword: ObservableField<String> = ObservableField()
    private var credentials: ObservableArrayList<UserItem> = ObservableArrayList()
    var navigationLiveData = MutableLiveData<Class<*>>()
    var status = MutableLiveData<Boolean?>()
    var empty = MutableLiveData<Boolean?>()
    private val TAG = "SignInViewModel"

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

    fun checkCredentials(view: View) {
        var isOk = false

        if (!livePassword.get().isNullOrEmpty() && !liveEmail.get().isNullOrEmpty()) {
            for (user: UserItem in credentials) {
                if (liveEmail.get() == user.email && livePassword.get() == user.password) {
                    isOk = true
                    break
                }
            }

            if (isOk) {
                Log.d(TAG, "Right Credentials")
                navigationLiveData.value = MainActivity::class.java
            } else {
                Log.d(TAG, "Wrong Credentials")
                status.value = true
            }
        } else {
            empty.value = true
        }
    }

    fun goRegister(view: View) {
        navigationLiveData.value = RegisterActivity::class.java
    }
}