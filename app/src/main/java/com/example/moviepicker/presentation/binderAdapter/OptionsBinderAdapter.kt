package com.example.moviepicker.presentation.binderAdapter

import android.content.SharedPreferences
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import com.example.moviepicker.presentation.viewmodel.OptionsViewModel

class OptionsBinderAdapter {
    companion object {
//        @BindingAdapter("fillSpinner")
//        @JvmStatic
//        fun setArrayToSpinner(spinner: Spinner, fillSpinner: String?) {
//            ArrayAdapter.createFromResource(
//                spinner.context,
//                R.array.language_array,
//                android.R.layout.simple_spinner_item
//            ).also { adapter ->
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                spinner.adapter = adapter
//            }
//
//        }

        @BindingAdapter("isChecked")
        @JvmStatic
        fun changeMode(switchCompat: SwitchCompat, sharedPreferences: SharedPreferences) {
            val checked =
                sharedPreferences
                    .getBoolean(OptionsViewModel.darkMode, false)

            switchCompat.isChecked = checked
        }
    }
}