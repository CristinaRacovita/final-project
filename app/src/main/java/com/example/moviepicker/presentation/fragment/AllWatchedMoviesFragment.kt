package com.example.moviepicker.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.MovieRemoteDataSource
import com.example.moviepicker.databinding.FragmentAllWatchedMoviesBinding
import com.example.moviepicker.domain.mediator.MovieMediator
import com.example.moviepicker.domain.useCase.FetchWatchedMoviesUseCase
import com.example.moviepicker.presentation.viewModelFactory.AllWatchedMoviesViewModelFactory
import com.example.moviepicker.presentation.viewmodel.AllWatchedMoviesViewModel

class AllWatchedMoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieRemoteDataSource = MovieRemoteDataSource(MoviePickerAPI.createAPI())
        val movieMediator = MovieMediator(movieRemoteDataSource)

        val sharedPreferences =
            requireActivity().getSharedPreferences(
                getString(R.string.preference_file_key),
                AppCompatActivity.MODE_PRIVATE
            )

        val allWatchedMoviesViewModel =
            ViewModelProvider(
                this,
                AllWatchedMoviesViewModelFactory(
                    FetchWatchedMoviesUseCase(movieMediator),
                    sharedPreferences
                )
            ).get(
                AllWatchedMoviesViewModel::class.java
            )
        val binding: FragmentAllWatchedMoviesBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_all_watched_movies,
                container,
                false
            )

        binding.viewModel = allWatchedMoviesViewModel

        return binding.root
    }
}