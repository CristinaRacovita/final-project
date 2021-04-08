package com.example.moviepicker.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.MovieRemoteDataSource
import com.example.moviepicker.databinding.FragmentHomeBinding
import com.example.moviepicker.domain.mediator.MovieMediator
import com.example.moviepicker.domain.useCase.GetRecommendedMovieUseCase
import com.example.moviepicker.presentation.viewModelFactory.HomeViewModelFactory
import com.example.moviepicker.presentation.viewmodel.HomeViewModel
import com.google.firebase.database.core.Context

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPreferences =
            this.requireActivity().getSharedPreferences(getString(R.string.preference_file_key), 0)

        val movieRemoteDataSource = MovieRemoteDataSource(MoviePickerAPI.createAPI())
        val movieMediator = MovieMediator(movieRemoteDataSource)

        val homeViewModel =
            ViewModelProvider(
                this,
                HomeViewModelFactory(parentFragmentManager, GetRecommendedMovieUseCase(movieMediator), sharedPreferences)
            ).get(
                HomeViewModel::class.java
            )
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.homeModel = homeViewModel

        return binding.root
    }
}