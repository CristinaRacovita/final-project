package com.example.moviepicker.presentation.binderAdapter

import android.widget.Button
import androidx.databinding.BindingAdapter

class ReviewWatchedMovieBinderAdapter {
    companion object {
        @BindingAdapter("stars")
        @JvmStatic
        fun setStars(button: Button, stars: Float) {
            button.isEnabled = stars >= 0.5
            button.isClickable = stars >= 0.5
        }

    }
}