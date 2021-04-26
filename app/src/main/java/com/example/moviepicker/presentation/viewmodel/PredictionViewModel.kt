package com.example.moviepicker.presentation.viewmodel

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.presentation.dialog.RecommendationDialog

class PredictionViewModel(private val fragmentManager: FragmentManager) : ViewModel() {
    var type = MutableLiveData<Boolean>()
    var genre = String
    var year = String

    fun getRecommendation() {
        val recommendationDialog: DialogFragment = RecommendationDialog()
        recommendationDialog.show(fragmentManager, "recommendation")
    }

//    fun onYearSelectItem(parent: AdapterView<Adapter>, view: View, pos: Int, id: Long) {
//        view as Spinner
//        genre = view.text
//    }
//
//    fun onGenreSelectItem(parent: AdapterView<Adapter>, view: View, pos: Int, id: Long) {
//
//    }
}