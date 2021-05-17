package com.example.moviepicker.presentation.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.work.WorkManager
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.MovieRemoteDataSource
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.mediator.MovieMediator
import com.example.moviepicker.domain.useCase.FetchUnratedMovies
import com.example.moviepicker.domain.workers.WatchedWorker.Companion.startWorker
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
            loggedAndRatedUser(sharedPreferences)
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

        toggleDarkMode(checked)
    }

    private fun loggedAndRatedUser(sharedPreferences: SharedPreferences): Intent {
        val currentUserId = sharedPreferences.getInt("id", -1)

        val movieDataSource = MovieRemoteDataSource(MoviePickerAPI.createAPI())
        val movieMediator = MovieMediator(movieDataSource)

        val moviesLive: LiveData<List<DisplayMovieItem>> =
            FetchUnratedMovies(movieMediator).getUnratedMovies(currentUserId)

        moviesLive.observeForever { items: List<DisplayMovieItem>? ->
            if (items != null && items.isNotEmpty()) {
                startWorker(items[0].title, items[0].id, WorkManager.getInstance(this))
            }
        }

        return Intent(this, MainActivity::class.java)
    }

    private fun toggleDarkMode(checked: Boolean) {
        if (checked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}