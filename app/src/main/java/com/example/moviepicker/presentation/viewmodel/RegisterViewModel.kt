package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.UserItem
import com.example.moviepicker.domain.useCase.AddUserUseCase
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.activity.MainActivity
import com.example.moviepicker.presentation.activity.SignInActivity
import java.math.BigInteger
import java.security.MessageDigest

class RegisterViewModel(
    private val addUserUseCase: AddUserUseCase,
    fetchCredentialsUseCase: FetchCredentialsUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    var liveEmail: ObservableField<String> = ObservableField()
    var livePassword: ObservableField<String> = ObservableField()
    private var credentials: ObservableArrayList<UserItem> = ObservableArrayList()
    var navigationLiveData = MutableLiveData<Class<*>>()
    var status = MutableLiveData<Boolean?>()
    var empty = MutableLiveData<Boolean?>()

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

    companion object {
        val auth_tag = "auth"
    }

    fun addNewUser(view: View) {
        if (!livePassword.get().isNullOrEmpty() && !liveEmail.get().isNullOrEmpty()) {
            if (!existsAlready(UserItem(email = liveEmail.get()!!, password = livePassword.get()!!))) {
                sharedPreferences.edit().putBoolean(auth_tag, true).apply()
                val md = MessageDigest.getInstance("MD5")
                val md5Password =
                    BigInteger(1, md.digest(livePassword.get()!!.toByteArray())).toString(16)
                        .padStart(32, '0')
                addUserUseCase.addUser(
                    UserItem(email = liveEmail.get()!!,password =  md5Password)
                )
                navigationLiveData.value = MainActivity::class.java
            } else {
                status.value = true
            }
        } else {
            empty.value = true
        }
    }

    fun goLogin(view: View) {
        navigationLiveData.value = SignInActivity::class.java
    }

    private fun existsAlready(userItem: UserItem): Boolean {
        for (user: UserItem in credentials) {
            if (user.email == userItem.email)
                return true
        }

        return false
    }
}