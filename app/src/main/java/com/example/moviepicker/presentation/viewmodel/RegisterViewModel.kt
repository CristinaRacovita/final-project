package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import android.view.View
import android.widget.EditText
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.UserItem
import com.example.moviepicker.domain.useCase.AddUserUseCase
import com.example.moviepicker.presentation.activity.ChooseMoviesActivity
import com.example.moviepicker.presentation.activity.RateMoviesActivity
import com.example.moviepicker.presentation.activity.SignInActivity
import java.math.BigInteger
import java.security.MessageDigest

class RegisterViewModel(
    private val addUserUseCase: AddUserUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    var liveEmail: ObservableField<String> = ObservableField()
    var livePassword: ObservableField<String> = ObservableField()

    var navigationLiveData = MutableLiveData<Class<*>>()
    var status = MutableLiveData<Boolean?>()
    var empty = MutableLiveData<Boolean?>()

    companion object {
        const val auth_tag = "auth"

        fun convertMD5(password: String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(password.toByteArray())).toString(16)
                .padStart(32, '0')
        }
    }

    fun addNewUser(usernameView: View, passwordView: View) {
        if (checkCredentials(usernameView, passwordView)) return

        if (!livePassword.get().isNullOrEmpty() && !liveEmail.get().isNullOrEmpty()) {
            val md5Password =
                convertMD5(livePassword.get()!!)

            val user = addUserUseCase.addUser(
                UserItem(email = liveEmail.get()!!, password = md5Password)
            )

            user.observeForever { u ->
                if (u != null) {
                    if (u.email != null && u.password != null && u.id != null) {
                        setFlags(u.id, u.email)
                    } else {
                        status.value = true
                    }
                }
            }

        } else {
            empty.value = true
        }
    }

    private fun setFlags(id: Int, email: String) {
        id.let { sharedPreferences.edit().putInt("id", it).apply() }
        email.let { sharedPreferences.edit().putString("username", it).apply() }

        sharedPreferences.edit().putBoolean(auth_tag, true).apply()
        sharedPreferences.edit().putBoolean(RateMoviesActivity.rated_tag, false).apply()

        navigationLiveData.value = ChooseMoviesActivity::class.java
    }

    private fun checkCredentials(
        usernameView: View,
        passwordView: View
    ): Boolean {
        if (liveEmail.get() == null || liveEmail.get()!!.isEmpty()) {
            usernameView as EditText
            usernameView.error = "Username Required"
            return true
        } else if (liveEmail.get()!!.length < 5) {
            usernameView as EditText
            usernameView.error = "Username too short"
            return true
        }

        if (livePassword.get() == null || livePassword.get()!!.isEmpty()) {
            passwordView as EditText
            passwordView.error = "Password Required"
            return true
        } else if (livePassword.get()!!.length < 5) {
            passwordView as EditText
            passwordView.error = "Password too short"
            return true
        }
        return false
    }

    fun goLogin() {
        navigationLiveData.value = SignInActivity::class.java
    }
}