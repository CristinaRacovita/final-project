package com.example.moviepicker.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.moviepicker.R
import com.example.moviepicker.presentation.viewmodel.OptionsViewModel
import com.example.moviepicker.presentation.viewmodel.RegisterViewModel

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences =
            this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)

        val intent: Intent?
        val logged = sharedPreferences.getBoolean(RegisterViewModel.auth_tag, false)
        val rated = sharedPreferences.getBoolean(RateMoviesActivity.rated_tag, false)

        intent = if (logged && rated) {
            Intent(this, MainActivity::class.java)
        } else if (logged) {
            Intent(this, ChooseMoviesActivity::class.java)
        } else {
            Intent(this, SignInActivity::class.java)
        }

        if (logged && !rated) {
            intent.putExtra(RegisterActivity.isWatched, false)
        }

        startActivity(intent)
        finish()

        val checked =
            this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)
                .getBoolean(OptionsViewModel.darkMode, false)
        if (checked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}