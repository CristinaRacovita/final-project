package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkManager
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.items.UserItem
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.domain.useCase.FetchUnratedMovies
import com.example.moviepicker.domain.workers.WatchedWorker
import com.example.moviepicker.presentation.activity.MainActivity
import com.example.moviepicker.presentation.activity.RegisterActivity


class SignInViewModel(
    private val fetchCredentialsUseCase: FetchCredentialsUseCase,
    private val sharedPreferences: SharedPreferences,
    private val fetchUnratedMovies: FetchUnratedMovies,
    private val workManager: WorkManager
) : ViewModel() {
    var liveEmail: ObservableField<String> = ObservableField()
    var livePassword: ObservableField<String> = ObservableField()
    var navigationLiveData = MutableLiveData<Class<*>>()
    var status = MutableLiveData<Boolean?>()
    var empty = MutableLiveData<Boolean?>()
    private val tag = "SignInViewModel"

    fun checkCredentials(usernameView: View, passwordView: View) {
        if (validationOfCredential(usernameView, passwordView)) return

        if (!livePassword.get().isNullOrEmpty() && !liveEmail.get().isNullOrEmpty()) {

            val liveUser = fetchCredentialsUseCase.checkUser(
                UserItem(
                    email = liveEmail.get(),
                    password = RegisterViewModel.convertMD5(livePassword.get()!!)
                )
            )

            liveUser.observeForever { user: UserItem? ->
                if (user != null) {
                    if (user.email != null) {
                        setFlags(user.id!!)
                    } else {
                        Log.d(tag, "Wrong Credentials")
                        status.value = true
                    }
                }
            }
        } else {
            empty.value = true
        }
    }

    private fun setFlags(id: Int) {
        sharedPreferences.edit().putBoolean(RegisterViewModel.auth_tag, true).apply()
        sharedPreferences.edit().putString("username", liveEmail.get()).apply()
        sharedPreferences.edit().putInt("id", id).apply()

        val moviesLive: LiveData<List<DisplayMovieItem>> =
            fetchUnratedMovies.getUnratedMovies(id)

        moviesLive.observeForever { items: List<DisplayMovieItem>? ->
            if (items != null && items.isNotEmpty()) {
                WatchedWorker.startWorker(
                    items[0].title,
                    items[0].id,
                    workManager
                )
            }
        }

        Log.d(tag, "Right Credentials")
        navigationLiveData.value = MainActivity::class.java
    }

    private fun validationOfCredential(
        usernameView: View,
        passwordView: View
    ): Boolean {
        if (liveEmail.get() == null || liveEmail.get()!!.isEmpty()) {
            usernameView as EditText
            usernameView.error = "Username Required"
            return true
        }

        if (livePassword.get() == null || livePassword.get()!!.isEmpty()) {
            passwordView as EditText
            passwordView.error = "Password Required"
            return true
        }
        return false
    }

    fun goRegister() {
        navigationLiveData.value = RegisterActivity::class.java
    }
}