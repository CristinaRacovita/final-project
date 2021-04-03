package com.example.moviepicker.presentation.binderAdapter

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.moviepicker.presentation.viewmodel.OptionsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainBinderAdapter {
    companion object {
        @BindingAdapter("fragment")
        @JvmStatic
        fun setFragment(bottomNavigationView: BottomNavigationView, fragment: Int) {
            val navHostFragment =
                (bottomNavigationView.context as AppCompatActivity).supportFragmentManager.findFragmentById(
                    fragment
                ) as NavHostFragment?

            NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment!!.navController
            )
        }
    }
}