package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.UserItem
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.activity.MainActivity
import com.example.moviepicker.presentation.activity.RegisterActivity
import java.math.BigInteger
import java.security.MessageDigest


class SignInViewModel(
    fetchCredentialsUseCase: FetchCredentialsUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    var liveEmail: ObservableField<String> = ObservableField()
    var livePassword: ObservableField<String> = ObservableField()
    private var credentials: ObservableArrayList<UserItem> = ObservableArrayList()
    var navigationLiveData = MutableLiveData<Class<*>>()
    var status = MutableLiveData<Boolean?>()
    var empty = MutableLiveData<Boolean?>()
    private val tag = "SignInViewModel"

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

    fun checkCredentials(usernameView: View, passwordView: View) {
        var isOk = false
        if (liveEmail.get() == null || liveEmail.get()!!.isEmpty()) {
            usernameView as EditText
            usernameView.error = "Username Required"
            return
        }

        if (livePassword.get() == null || livePassword.get()!!.isEmpty()) {
            passwordView as EditText
            passwordView.error = "Password Required"
            return
        }

        if (!livePassword.get().isNullOrEmpty() && !liveEmail.get().isNullOrEmpty()) {
            for (user: UserItem in credentials) {
                val md = MessageDigest.getInstance("MD5")
                val md5Password =
                    BigInteger(1, md.digest(livePassword.get()!!.toByteArray())).toString(16)
                        .padStart(32, '0')
                if (liveEmail.get() == user.email && md5Password == user.password) {
                    isOk = true
                    break
                }
            }

            if (isOk) {
                sharedPreferences.edit().putBoolean(RegisterViewModel.auth_tag, true).apply()

                Log.d(tag, "Right Credentials")
                navigationLiveData.value = MainActivity::class.java
            } else {
                Log.d(tag, "Wrong Credentials")
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