package com.example.moviepicker.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.databinding.ActivityMainBinding
import com.example.moviepicker.presentation.viewModelFactory.MainViewModelFactory
import com.example.moviepicker.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory()
            ).get(
                MainViewModel::class.java
            )
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.model = mainViewModel
    }
}