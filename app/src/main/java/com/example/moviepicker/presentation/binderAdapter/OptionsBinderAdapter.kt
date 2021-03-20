package com.example.moviepicker.presentation.binderAdapter

import android.content.SharedPreferences
import android.content.res.Configuration
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import com.example.moviepicker.R
import com.example.moviepicker.presentation.viewmodel.OptionsViewModel
import java.util.*

class OptionsBinderAdapter {
    companion object {
        @BindingAdapter("fillSpinner")
        @JvmStatic
        fun setArrayToSpinner(spinner: Spinner, fillSpinner: String?) {
            ArrayAdapter.createFromResource(
                spinner.context,
                R.array.language_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }

        }

        @BindingAdapter("isChecked")
        @JvmStatic
        fun changeMode(switchCompat: SwitchCompat, sharedPreferences: SharedPreferences) {
            val checked =
                sharedPreferences
                    .getBoolean(OptionsViewModel.darkMode, false)

            switchCompat.isChecked = checked
        }

//        @BindingAdapter("language")
//        @JvmStatic
//        fun changeLanguage(spinner: Spinner, sharedPreferences: SharedPreferences) {
//            val position = spinner.selectedItemPosition
//            var language: String = "ro"
//            if(position == 0){
//                language="en"
//            }
//
//            val locale = Locale(language)
//            Locale.setDefault(locale)
//            val config = Configuration()
//            config.setLocale(locale)
//
//            spinner.context.resources.updateConfiguration(config,spinner.context.resources.getDisplayMetrics())
//        }
    }
}