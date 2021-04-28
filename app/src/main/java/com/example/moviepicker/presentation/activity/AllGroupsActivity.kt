package com.example.moviepicker.presentation.activity

import android.content.Intent
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
    companion object {
        const val ALL_GROUPS_FLAG = "flag_all_groups"
    }

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

        groupViewModel.navigationLiveData.observe(this, { myClass ->
            myClass?.let {
                val intent = Intent(this, myClass)
                intent.putExtra(ALL_GROUPS_FLAG, true)
                intent.putExtra("groupName", groupViewModel.selectedGroupName.value)
                intent.putExtra("users", groupViewModel.selectedGroupUsers.value)
                intent.putExtra(ALL_GROUPS_FLAG, true)

                startActivity(intent)
                groupViewModel.navigationLiveData.value = null
            }
        })
    }
}