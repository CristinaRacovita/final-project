package com.example.moviepicker.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.DisplayMovieRemoteDataSource
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.databinding.ActivityChooseMoviesBinding
import com.example.moviepicker.domain.mediator.DisplayMovieMediator
import com.example.moviepicker.domain.useCase.FetchDisplayMovieUseCase
import com.example.moviepicker.presentation.viewModelFactory.ChooseViewModelFactory
import com.example.moviepicker.presentation.viewmodel.ChooseMoviesViewModel

class ChooseMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_movies)

        val remoteDataSource = DisplayMovieRemoteDataSource(MoviePickerAPI.createAPI())
        val mediator = DisplayMovieMediator(remoteDataSource)

        val chooseMoviesViewModel =
            ViewModelProvider(
                this,
                ChooseViewModelFactory(FetchDisplayMovieUseCase(mediator))
            ).get(
                ChooseMoviesViewModel::class.java
            )

        val binding: ActivityChooseMoviesBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_choose_movies)
        binding.viewModel = chooseMoviesViewModel

    }
}