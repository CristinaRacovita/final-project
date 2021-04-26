package com.example.moviepicker.presentation.viewmodel

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.presentation.dialog.RecommendationDialog

class PredictionViewModel(
    private val fragmentManager: FragmentManager,
    val genresArray: Array<String>
) : ViewModel() {
    var type = MutableLiveData<Boolean>()
    var genreItemPosition = MutableLiveData<Int>()
    var yearItemPosition = MutableLiveData<Int>()

    val yearsArray = createYearsArray()

    fun getRecommendation() {
        val genre =
            if (genreItemPosition.value != 0) genresArray[genreItemPosition.value!!] else null
        val year = if (yearItemPosition.value != 0) yearsArray[yearItemPosition.value!!] else null

        val recommendationDialog: DialogFragment = RecommendationDialog(genre, year)
        recommendationDialog.show(fragmentManager, "recommendation")
    }

    private fun createYearsArray(): Array<String> {
        val years: Array<String> = Array(11) { "" }
        var value = 1910
        var k = 1

        years[0] = "Nothing"

        while (k != 11) {
            years[k] = value.toString()
            k++
            value += 10
        }

        return years
    }
}