package com.example.moviepicker.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.MovieRemoteDataSource
import com.example.moviepicker.data.remote.RatingRemoteSource
import com.example.moviepicker.databinding.ActivityRateMoviesBinding
import com.example.moviepicker.domain.mediator.MovieMediator
import com.example.moviepicker.domain.mediator.RatingMediator
import com.example.moviepicker.domain.useCase.AddRatingsUseCase
import com.example.moviepicker.domain.useCase.FetchMovieDetailsUseCase
import com.example.moviepicker.presentation.viewModelFactory.RateMoviesViewModelFactory
import com.example.moviepicker.presentation.viewmodel.DisplayMovieItemViewModel
import com.example.moviepicker.presentation.viewmodel.RateMoviesViewModel

class RateMoviesActivity : AppCompatActivity() {
    companion object {
        const val rated_tag = "rated"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_movies)
        val sharedPreferences =
            this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)

        val picked = intent.getSerializableExtra("picked") as List<DisplayMovieItemViewModel>?

        val movieRemoteDataSource = MovieRemoteDataSource(MoviePickerAPI.createAPI())
        val movieMediator = MovieMediator(movieRemoteDataSource)

        val ratingRemoteDataSource = RatingRemoteSource(MoviePickerAPI.createAPI())
        val ratingMediator = RatingMediator(ratingRemoteDataSource)

        val rateMoviesViewModel =
            ViewModelProvider(
                this,
                RateMoviesViewModelFactory(
                    FetchMovieDetailsUseCase(movieMediator),
                    AddRatingsUseCase(ratingMediator),
                    sharedPreferences,
                    picked!!
                )
            ).get(
                RateMoviesViewModel::class.java
            )

        val binding: ActivityRateMoviesBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_rate_movies)
        binding.viewModel = rateMoviesViewModel

        rateMoviesViewModel.navigationLiveData.observe(this, { myClass ->
            myClass?.let {
                startActivity(Intent(this, myClass))
                sharedPreferences.edit().putBoolean(rated_tag, true).apply()
                rateMoviesViewModel.navigationLiveData.value = null
            }
        })
    }
}