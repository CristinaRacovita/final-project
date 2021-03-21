package com.example.moviepicker.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.RemoteDataSource
import com.example.moviepicker.databinding.GroupMenuBinding
import com.example.moviepicker.domain.UserMediator
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.viewModelFactory.AlertDialogViewModelFactory
import com.example.moviepicker.presentation.viewmodel.AlertDialogViewModel

class CreateGroupAlertDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog = AlertDialog.Builder(requireContext())

        val remoteDataSource = RemoteDataSource(MoviePickerAPI.createAPI())
        val mediator = UserMediator(remoteDataSource)

        val alertDialogViewModel =
            ViewModelProvider(
                this,
                AlertDialogViewModelFactory(
                    FetchCredentialsUseCase(mediator)
                )
            ).get(
                AlertDialogViewModel::class.java
            )

        val binding: GroupMenuBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.group_menu,
                null,
                false
            )

        binding.viewModel = alertDialogViewModel

        alertDialog.setView(binding.root)

        return alertDialog.create()
    }
}