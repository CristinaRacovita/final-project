package com.example.moviepicker.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.MovieRemoteDataSource
import com.example.moviepicker.databinding.ActivityChooseMoviesBinding
import com.example.moviepicker.domain.mediator.MovieMediator
import com.example.moviepicker.domain.useCase.FetchDisplayMovieUseCase
import com.example.moviepicker.domain.useCase.FetchWatchedMoviesUseCase
import com.example.moviepicker.presentation.viewModelFactory.ChooseViewModelFactory
import com.example.moviepicker.presentation.viewmodel.ChooseMoviesViewModel

class ChooseMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_movies)

        val sharedPreferences =
            this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)

        val watched = intent.getBooleanExtra(RegisterActivity.isWatched, true)

        val remoteDataSource = MovieRemoteDataSource(MoviePickerAPI.createAPI())
        val mediator = MovieMediator(remoteDataSource)

        val chooseMoviesViewModel =
            ViewModelProvider(
                this,
                ChooseViewModelFactory(
                    watched,
                    FetchDisplayMovieUseCase(mediator),
                    sharedPreferences,
                    FetchWatchedMoviesUseCase(mediator)
                )
            ).get(
                ChooseMoviesViewModel::class.java
            )

        val binding: ActivityChooseMoviesBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_choose_movies)
        binding.viewModel = chooseMoviesViewModel

        chooseMoviesViewModel.navigationLiveData.observe(this, { myClass ->
            myClass?.let {
                val intent = Intent(this, myClass)
                intent.putExtra("picked", chooseMoviesViewModel.pickedMovies)
                startActivity(intent)

                chooseMoviesViewModel.navigationLiveData.value = null
                finish()
            }
        })

    }
}