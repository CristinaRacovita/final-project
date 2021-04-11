package com.example.moviepicker.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.MovieRemoteDataSource
import com.example.moviepicker.databinding.DialogRecommendationBinding
import com.example.moviepicker.domain.mediator.MovieMediator
import com.example.moviepicker.domain.useCase.GetRecommendedMovieUseCase
import com.example.moviepicker.presentation.viewModelFactory.RecommendedMovieViewModelFactory
import com.example.moviepicker.presentation.viewmodel.RecommendedMovieViewModel

class RecommendationDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        val sharedPreferences =
            this.requireActivity().getSharedPreferences(getString(R.string.preference_file_key), 0)

        val movieRemoteDataSource = MovieRemoteDataSource(MoviePickerAPI.createAPI())
        val movieMediator = MovieMediator(movieRemoteDataSource)

        val recommendationViewModel =
            ViewModelProvider(
                this,
                RecommendedMovieViewModelFactory(
                    sharedPreferences,
                    GetRecommendedMovieUseCase(movieMediator)
                )
            ).get(
                RecommendedMovieViewModel::class.java
            )
        val binding: DialogRecommendationBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.dialog_recommendation,
                null,
                false
            )

        binding.viewModel = recommendationViewModel

        builder.setView(binding.root)

        return builder.create()
    }
}