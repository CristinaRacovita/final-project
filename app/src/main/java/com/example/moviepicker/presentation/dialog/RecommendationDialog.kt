package com.example.moviepicker.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.GroupRemoteDataSource
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.MovieRemoteDataSource
import com.example.moviepicker.data.remote.RatingRemoteSource
import com.example.moviepicker.databinding.DialogRecommendationBinding
import com.example.moviepicker.domain.mediator.GroupMediator
import com.example.moviepicker.domain.mediator.MovieMediator
import com.example.moviepicker.domain.mediator.RatingMediator
import com.example.moviepicker.domain.useCase.AddRatingsUseCase
import com.example.moviepicker.domain.useCase.GetRecommendedMovieUseCase
import com.example.moviepicker.domain.useCase.GroupUseCase
import com.example.moviepicker.presentation.viewModelFactory.RecommendedMovieViewModelFactory
import com.example.moviepicker.presentation.viewmodel.RecommendedMovieViewModel

class RecommendationDialog() :
    DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        WorkManager.getInstance(requireContext()).pruneWork()

        val builder = AlertDialog.Builder(requireContext())

        val type: Int = arguments?.getInt("type", 0)!!

        val genre: String? = arguments?.getString("genre")
        val year: String? = arguments?.getString("year")
        val ids: String? = arguments?.getString("ids")
        val groupId: Int = arguments?.getInt("groupId", -1)!!

        val sharedPreferences =
            this.requireActivity().getSharedPreferences(getString(R.string.preference_file_key), 0)

        val movieRemoteDataSource = MovieRemoteDataSource(MoviePickerAPI.createAPI())
        val movieMediator = MovieMediator(movieRemoteDataSource)

        val ratingRemoteDataSource = RatingRemoteSource(MoviePickerAPI.createAPI())
        val ratingMediator = RatingMediator(ratingRemoteDataSource)

        val groupRemoteDataSource = GroupRemoteDataSource(MoviePickerAPI.createAPI())
        val groupMediator = GroupMediator(groupRemoteDataSource)

        val recommendationViewModel =
            ViewModelProvider(
                this,
                RecommendedMovieViewModelFactory(
                    sharedPreferences,
                    GetRecommendedMovieUseCase(movieMediator),
                    WorkManager.getInstance(requireActivity()),
                    genre, year, type, ids, groupId,
                    AddRatingsUseCase(ratingMediator),
                    GroupUseCase(groupMediator)
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

        recommendationViewModel.closedLiveData.observe(this, { closed ->
            closed?.let {
                if (closed) {
                    this.dismiss()

                    Toast.makeText(
                        context,
                        requireContext().getString(R.string.movie_added),
                        Toast.LENGTH_LONG
                    ).show()

                    recommendationViewModel.closedLiveData.value = false
                }
            }
        })


        return builder.create()
    }


}