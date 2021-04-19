package com.example.moviepicker.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.UserRemoteDataSource
import com.example.moviepicker.databinding.ActivityRegisterBinding
import com.example.moviepicker.domain.mediator.UserMediator
import com.example.moviepicker.domain.useCase.AddUserUseCase
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.viewModelFactory.RegisterViewModelFactory
import com.example.moviepicker.presentation.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    companion object {
        const val isWatched = "watched"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val remoteDataSource = UserRemoteDataSource(MoviePickerAPI.createAPI())
        val mediator = UserMediator(remoteDataSource)
        val sharedPreferences =
            this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)

        val registerViewModel =
            ViewModelProvider(
                this,
                RegisterViewModelFactory(
                    FetchCredentialsUseCase(mediator),
                    AddUserUseCase(mediator),
                    sharedPreferences
                )
            ).get(
                RegisterViewModel::class.java
            )

        val binding: ActivityRegisterBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel = registerViewModel

        registerViewModel.status.observe(this, { status ->
            status?.let {
                registerViewModel.status.value = null
                Toast.makeText(this, getString(R.string.already_exists), Toast.LENGTH_LONG)
                    .show()
            }
        })

        registerViewModel.empty.observe(this, { status ->
            status?.let {
                registerViewModel.empty.value = null
                Toast.makeText(this, getString(R.string.completed), Toast.LENGTH_LONG).show()
            }
        })

        registerViewModel.navigationLiveData.observe(this, { myClass ->
            myClass?.let {
                val intent = Intent(this, myClass)
                intent.putExtra(isWatched, false)
                startActivity(intent)
                if (myClass == ChooseMoviesActivity::class.java) {
                    Toast.makeText(
                        this,
                        getString(R.string.new_user_successfully),
                        Toast.LENGTH_LONG
                    )
                        .show()
                    finish()
                }
                registerViewModel.navigationLiveData.value = null
            }
        })
    }
}