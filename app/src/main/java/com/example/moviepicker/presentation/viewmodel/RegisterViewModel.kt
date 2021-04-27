package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import android.view.View
import android.widget.EditText
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.UserItem
import com.example.moviepicker.domain.useCase.AddUserUseCase
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.activity.ChooseMoviesActivity
import com.example.moviepicker.presentation.activity.RateMoviesActivity
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
        val liveItems: LiveData<List<UserItem>> = fetchCredentialsUseCase.getUsers()

        liveItems.observeForever { items: List<UserItem?>? ->
            if (items != null) {
                this.credentials.addAll(
                    items
                )
            }

        }
    }

    companion object {
        const val auth_tag = "auth"
    }

    fun addNewUser(usernameView: View, passwordView: View) {
        if (liveEmail.get() == null || liveEmail.get()!!.isEmpty()) {
            usernameView as EditText
            usernameView.error = "Username Required"
            return
        } else if (liveEmail.get()!!.length < 5) {
            usernameView as EditText
            usernameView.error = "Username too short"
            return
        }

        if (livePassword.get() == null || livePassword.get()!!.isEmpty()) {
            passwordView as EditText
            passwordView.error = "Password Required"
            return
        } else if (livePassword.get()!!.length < 5) {
            passwordView as EditText
            passwordView.error = "Password too short"
            return
        }

        if (!livePassword.get().isNullOrEmpty() && !liveEmail.get().isNullOrEmpty()) {
            if (!existsAlready(
                    UserItem(
                        email = liveEmail.get()!!,
                        password = livePassword.get()!!
                    )
                )
            ) {
                sharedPreferences.edit().putBoolean(auth_tag, true).apply()
                sharedPreferences.edit().putBoolean(RateMoviesActivity.rated_tag, false).apply()

                val md = MessageDigest.getInstance("MD5")
                val md5Password =
                    BigInteger(1, md.digest(livePassword.get()!!.toByteArray())).toString(16)
                        .padStart(32, '0')

                val user = addUserUseCase.addUser(
                    UserItem(email = liveEmail.get()!!, password = md5Password)
                )

                user.observeForever { u ->
                    if (u != null) {
                        u.id?.let { sharedPreferences.edit().putInt("id", it).apply() }
                        u.email?.let { sharedPreferences.edit().putString("username", it).apply() }
                    }
                }

                navigationLiveData.value = ChooseMoviesActivity::class.java
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