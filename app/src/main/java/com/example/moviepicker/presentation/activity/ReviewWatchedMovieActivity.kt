package com.example.moviepicker.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.MovieRemoteDataSource
import com.example.moviepicker.data.remote.RatingRemoteSource
import com.example.moviepicker.databinding.ActivityReviewWatchedMovieBinding
import com.example.moviepicker.domain.mediator.MovieMediator
import com.example.moviepicker.domain.mediator.RatingMediator
import com.example.moviepicker.domain.useCase.AddRatingsUseCase
import com.example.moviepicker.domain.useCase.FetchMovieDetailsUseCase
import com.example.moviepicker.presentation.viewModelFactory.ReviewWatchedMovieViewModelFactory
import com.example.moviepicker.presentation.viewmodel.ReviewWatchedMovieViewModel

class ReviewWatchedMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_watched_movie)

        val sharedPreferences =
            this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)

        val movieRemoteDataSource = MovieRemoteDataSource(MoviePickerAPI.createAPI())
        val movieMediator = MovieMediator(movieRemoteDataSource)

        val ratingRemoteDataSource = RatingRemoteSource(MoviePickerAPI.createAPI())
        val ratingMediator = RatingMediator(ratingRemoteDataSource)

        val reviewViewModel =
            ViewModelProvider(
                this,
                ReviewWatchedMovieViewModelFactory(
                    FetchMovieDetailsUseCase(movieMediator),
                    AddRatingsUseCase(ratingMediator),
                    sharedPreferences
                )
            ).get(
                ReviewWatchedMovieViewModel::class.java
            )
        val binding: ActivityReviewWatchedMovieBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_review_watched_movie)
        binding.viewModel = reviewViewModel

        reviewViewModel.navigationLiveData.observe(this, { myClass ->
            myClass?.let {
                startActivity(Intent(this, myClass))
                reviewViewModel.navigationLiveData.value = null

                Toast.makeText(this, getString(R.string.thank), Toast.LENGTH_LONG).show()

                finish()
            }
        })
    }
}