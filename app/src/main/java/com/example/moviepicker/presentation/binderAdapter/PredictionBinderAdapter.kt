package com.example.moviepicker.presentation.binderAdapter

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.example.moviepicker.R

class PredictionBinderAdapter {
    companion object {
        @BindingAdapter("genres")
        @JvmStatic
        fun setSpinnerGenresArray(spinner: Spinner, type: Boolean) {
            val array = spinner.context.resources.getStringArray(R.array.genres)

            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                spinner.context,
                android.R.layout.simple_spinner_dropdown_item,
                array
            )
            spinner.adapter = arrayAdapter
        }

        @BindingAdapter("years")
        @JvmStatic
        fun setSpinnerYearArray(spinner: Spinner, type: Boolean) {
            val array: Array<String> = createYearsArray()

            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                spinner.context,
                android.R.layout.simple_spinner_dropdown_item,
                array
            )
            spinner.adapter = arrayAdapter
        }

        private fun createYearsArray(): Array<String> {
            val years: Array<String> = Array(11) { "" }
            var value = 1910
            var k = 0

            while (k != 11) {
                years[k] = value.toString()
                k++
                value += 10
            }

            return years
        }
    }
}