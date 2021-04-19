package com.example.moviepicker.presentation.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import java.io.Serializable


class GroupItemViewModel: Serializable {
    var userId = ObservableInt()
    var username = ObservableField<String>()
    var isChecked = ObservableBoolean()
}