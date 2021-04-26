package com.example.moviepicker.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.databinding.FragmentPredictionBinding
import com.example.moviepicker.presentation.viewModelFactory.PredictionViewModelFactory
import com.example.moviepicker.presentation.viewmodel.PredictionViewModel

class PredictionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModel =
            ViewModelProvider(
                this,
                PredictionViewModelFactory(
                    requireActivity().supportFragmentManager
                )
            ).get(
                PredictionViewModel::class.java
            )
        val binding: FragmentPredictionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_prediction, container, false)

        binding.viewModel = viewModel

        return binding.root
    }
}