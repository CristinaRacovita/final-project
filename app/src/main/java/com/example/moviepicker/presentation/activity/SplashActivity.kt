package com.example.moviepicker.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviepicker.R
import com.example.moviepicker.presentation.viewmodel.RegisterViewModel

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences =
            this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)

        val intent: Intent?
        val logged = sharedPreferences.getBoolean(RegisterViewModel.auth_tag, false)

        intent = if (logged) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, SignInActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}