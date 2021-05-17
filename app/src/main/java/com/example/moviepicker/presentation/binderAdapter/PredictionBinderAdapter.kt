package com.example.moviepicker.presentation.binderAdapter

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter

class PredictionBinderAdapter {
    companion object {
        @BindingAdapter("array")
        @JvmStatic
        fun setSpinnerGenresArray(spinner: Spinner, array: Array<String>) {
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                spinner.context,
                android.R.layout.simple_spinner_dropdown_item,
                array
            )
            spinner.adapter = arrayAdapter
        }
    }
}