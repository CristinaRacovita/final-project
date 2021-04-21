package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.moviepicker.domain.workers.EmailWorker
import com.example.moviepicker.presentation.activity.SignInActivity
import java.util.concurrent.TimeUnit

class OptionsViewModel(
    val sharedPreferences: SharedPreferences,
    private val workManager: WorkManager,
) : ViewModel() {
    var navigationLiveData = MutableLiveData<Class<*>>()
    var username = ObservableField<String>()
    var profileImageUri = ObservableField<String>()
    var changeImage = MutableLiveData<Boolean>()

    init {
        username.set(sharedPreferences.getString("username", ""))
        profileImageUri.set(sharedPreferences.getString("profile", ""))
    }

    companion object {
        const val darkMode: String = "DarkMode"
    }

    fun logout(view: View) {
        sharedPreferences.edit().putBoolean(RegisterViewModel.auth_tag, false).apply()
        sharedPreferences.edit().putString("username", "").apply()
        sharedPreferences.edit().putString("profile", "").apply()
        sharedPreferences.edit().putInt("id", -1).apply()

        navigationLiveData.value = SignInActivity::class.java
    }

    fun onCheckedChanged(view: View, checked: Boolean) {
        sharedPreferences.edit().putBoolean(darkMode, checked).apply()

        if (checked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun sendEmail() {
        val sendEmailWorkRequest =
            OneTimeWorkRequest.Builder(EmailWorker::class.java)
                .setInitialDelay(1, TimeUnit.SECONDS)
                .build()

        workManager.enqueue(
            sendEmailWorkRequest
        )
    }

    fun addProfileUri() {
        changeImage.value = true
    }
}