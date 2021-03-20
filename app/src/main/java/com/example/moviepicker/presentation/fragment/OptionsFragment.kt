package com.example.moviepicker.presentation.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.databinding.FragmentOptionsBinding
import com.example.moviepicker.presentation.viewModelFactory.OptionsViewModelFactory
import com.example.moviepicker.presentation.viewmodel.OptionsViewModel


class OptionsFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreferences =
            requireActivity().getSharedPreferences(
                getString(R.string.preference_file_key),
                AppCompatActivity.MODE_PRIVATE
            )



        val optionsViewModel =
            ViewModelProvider(
                this,
                OptionsViewModelFactory(sharedPreferences)
            ).get(
                OptionsViewModel::class.java
            )
        val binding: FragmentOptionsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_options, container, false)

        binding.optionsViewModel = optionsViewModel

        optionsViewModel.navigationLiveData.observe(requireActivity(), { myClass ->
            myClass?.let {
                startActivity(Intent(requireContext(), myClass))
                requireActivity().finish()

                optionsViewModel.navigationLiveData.value = null
            }
        })

        return binding.root
    }
}