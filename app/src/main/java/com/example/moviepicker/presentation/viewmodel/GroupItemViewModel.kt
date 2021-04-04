package com.example.moviepicker.presentation.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel


class GroupItemViewModel : ViewModel() {
    var username = ObservableField<String>()
    var isChecked = ObservableBoolean()

    fun selectUser(view: View, groupItemViewModel: GroupItemViewModel, checked: Boolean) {
        Log.d("ItemViewModel", "${groupItemViewModel.username.get()} is selected: $checked")
    }
}