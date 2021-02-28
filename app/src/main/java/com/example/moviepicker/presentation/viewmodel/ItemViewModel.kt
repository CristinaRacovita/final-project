package com.example.moviepicker.presentation.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel


class ItemViewModel : ViewModel() {
    var username = ObservableField<String>()
    var isChecked = ObservableBoolean()

    fun selectUser(view: View, itemViewModel: ItemViewModel, checked: Boolean) {
        Log.d("ItemViewModel", "${itemViewModel.username.get()} is selected: $checked")
    }
}