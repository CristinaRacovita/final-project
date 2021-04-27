package com.example.moviepicker.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.GroupRemoteDataSource
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.databinding.ActivityAllGroupsBinding
import com.example.moviepicker.domain.mediator.GroupMediator
import com.example.moviepicker.domain.useCase.FetchGroupsUseCase
import com.example.moviepicker.presentation.viewModelFactory.AllGroupsViewModelFactory
import com.example.moviepicker.presentation.viewmodel.AllGroupsViewModel

class AllGroupsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences =
            this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)

        val groupRemoteDataSource = GroupRemoteDataSource(MoviePickerAPI.createAPI())
        val groupMediator = GroupMediator(groupRemoteDataSource)

        val groupViewModel =
            ViewModelProvider(
                this,
                AllGroupsViewModelFactory(sharedPreferences, FetchGroupsUseCase(groupMediator))
            ).get(
                AllGroupsViewModel::class.java
            )
        val binding: ActivityAllGroupsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_all_groups)

        binding.viewModel = groupViewModel
        setContentView(R.layout.activity_all_groups)
    }
}