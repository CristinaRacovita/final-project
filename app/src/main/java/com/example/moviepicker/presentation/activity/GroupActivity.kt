package com.example.moviepicker.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.MovieRemoteDataSource
import com.example.moviepicker.data.remote.UserRemoteDataSource
import com.example.moviepicker.databinding.ActivityGroupBinding
import com.example.moviepicker.domain.items.UserDetailsItem
import com.example.moviepicker.domain.mediator.MovieMediator
import com.example.moviepicker.domain.mediator.UserMediator
import com.example.moviepicker.domain.useCase.FetchDisplayMovieUseCase
import com.example.moviepicker.domain.useCase.FetchUserDetailsUseCase
import com.example.moviepicker.presentation.viewModelFactory.GroupViewModelFactory
import com.example.moviepicker.presentation.viewmodel.GroupItemViewModel
import com.example.moviepicker.presentation.viewmodel.GroupViewModel

class GroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        val flag = intent.getBooleanExtra(AllGroupsActivity.ALL_GROUPS_FLAG, false)

        val users = if (flag) {
            intent.getSerializableExtra("users") as List<UserDetailsItem>?

        } else {
            intent.getSerializableExtra("selectedUsers") as List<GroupItemViewModel>?
        }

        val groupName = intent.getStringExtra("groupName")
        val groupId = intent.getIntExtra("groupId", -1)

        val remoteDataSource = UserRemoteDataSource(MoviePickerAPI.createAPI())
        val mediator = UserMediator(remoteDataSource)

        val movieDataSource = MovieRemoteDataSource(MoviePickerAPI.createAPI())
        val movieMediator = MovieMediator(movieDataSource)

        val viewModel =
            ViewModelProvider(
                this,
                GroupViewModelFactory(
                    groupName!!,
                    FetchUserDetailsUseCase(mediator),
                    flag,
                    users!!,
                    supportFragmentManager,
                    groupId,
                    FetchDisplayMovieUseCase(movieMediator)
                )
            ).get(
                GroupViewModel::class.java
            )
        val binding: ActivityGroupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_group)

        viewModel.navigationLiveData.observe(this, { myClass ->
            myClass?.let {
                val intent = Intent(this, myClass)
                startActivity(intent)
                viewModel.navigationLiveData.value = null
            }
        })

        binding.viewModel = viewModel

    }
}