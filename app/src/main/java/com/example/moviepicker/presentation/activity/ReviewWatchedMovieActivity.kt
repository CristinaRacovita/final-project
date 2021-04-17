package com.example.moviepicker.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviepicker.R
import com.example.moviepicker.presentation.viewmodel.DisplayMovieItemViewModel

class ReviewWatchedMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_watched_movie)

        val movieId = intent.getIntExtra("movieId", -1)
    }
}