package com.example.moviepicker.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.RemoteDataSource
import com.example.moviepicker.databinding.ActivitySignInBinding
import com.example.moviepicker.domain.UserMediator
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.viewModelFactory.SignInViewModelFactory
import com.example.moviepicker.presentation.viewmodel.SignInViewModel

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val remoteDataSource = RemoteDataSource(MoviePickerAPI.createAPI())
        val mediator = UserMediator(remoteDataSource)

        val loginViewModel =
            ViewModelProvider(this, SignInViewModelFactory(FetchCredentialsUseCase(mediator))).get(
                SignInViewModel::class.java
            )

        val binding: ActivitySignInBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.viewModel = loginViewModel

        loginViewModel.status.observe(this, { status ->
            status?.let {
                loginViewModel.status.value = null
                Toast.makeText(this, getString(R.string.email_pass_incorrect), Toast.LENGTH_LONG)
                    .show()
            }
        })

        loginViewModel.empty.observe(this, { status ->
            status?.let {
                loginViewModel.empty.value = null
                Toast.makeText(this, getString(R.string.completed), Toast.LENGTH_LONG).show()
            }
        })

        loginViewModel.navigationLiveData.observe(this, { myClass ->
            myClass?.let {
                startActivity(Intent(this, myClass))
                loginViewModel.navigationLiveData.value = null
            }
        })
    }
}