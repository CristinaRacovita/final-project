package com.example.moviepicker.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.moviepicker.R
import com.example.moviepicker.presentation.viewmodel.RegisterViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun logout(view: View) {
        val sharedPreferences =
            this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(RegisterViewModel.auth_tag, false).apply()

        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }
}