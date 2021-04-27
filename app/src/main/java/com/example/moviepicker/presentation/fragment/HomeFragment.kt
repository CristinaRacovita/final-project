package com.example.moviepicker.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.databinding.FragmentHomeBinding
import com.example.moviepicker.presentation.viewModelFactory.HomeViewModelFactory
import com.example.moviepicker.presentation.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(
                this,
                HomeViewModelFactory(parentFragmentManager)
            ).get(
                HomeViewModel::class.java
            )
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        homeViewModel.navigationLiveData.observe(requireActivity(), { myClass ->
            myClass?.let {
                val intent = Intent(requireContext(), myClass)
                startActivity(intent)
                homeViewModel.navigationLiveData.value = null
            }
        })

        binding.homeModel = homeViewModel

        return binding.root
    }


}