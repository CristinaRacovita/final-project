package com.example.moviepicker.presentation.binderAdapter

import android.content.SharedPreferences
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import com.example.moviepicker.presentation.viewmodel.OptionsViewModel

class OptionsBinderAdapter {
    companion object {
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