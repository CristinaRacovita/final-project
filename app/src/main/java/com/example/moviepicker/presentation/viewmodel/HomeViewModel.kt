package com.example.moviepicker.presentation.viewmodel

import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.moviepicker.presentation.dialog.RecommendationDialog

class HomeViewModel(
    val fragmentManager: FragmentManager
) : ViewModel() {
    fun getRecommendation(view: View) {
        val recommendationDialog: DialogFragment = RecommendationDialog()
        recommendationDialog.show(fragmentManager, "recommendation")
    }
}