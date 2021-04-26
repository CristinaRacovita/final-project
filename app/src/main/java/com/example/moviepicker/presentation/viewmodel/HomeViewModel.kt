package com.example.moviepicker.presentation.viewmodel

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.moviepicker.presentation.fragment.HomeFragmentDirections

class HomeViewModel(
    val fragmentManager: FragmentManager
) : ViewModel() {
    fun getRecommendation(view: View) {
        val action: NavDirections =
            HomeFragmentDirections.actionHomeActionToPredictionFragment()
        view.findNavController().navigate(action)
    }

    fun goToAllMovies(view: View) {
        val action: NavDirections = HomeFragmentDirections.actionHomeActionToAllMoviesButton()
        view.findNavController().navigate(action)
    }
}