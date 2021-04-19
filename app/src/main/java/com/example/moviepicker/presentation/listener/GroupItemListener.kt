package com.example.moviepicker.presentation.listener

import android.view.View
import com.example.moviepicker.presentation.viewmodel.GroupItemViewModel

interface GroupItemListener {
    fun selectUser(view: View, groupItemViewModel: GroupItemViewModel)
}