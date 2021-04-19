package com.example.moviepicker.presentation.dialog

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.UserRemoteDataSource
import com.example.moviepicker.databinding.AlertGroupMenuBinding
import com.example.moviepicker.domain.mediator.UserMediator
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.viewModelFactory.AlertDialogViewModelFactory
import com.example.moviepicker.presentation.viewmodel.AlertDialogViewModel

class CreateGroupAlertDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog = AlertDialog.Builder(requireContext())

        val remoteDataSource = UserRemoteDataSource(MoviePickerAPI.createAPI())
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

        val binding: AlertGroupMenuBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.alert_group_menu,
                null,
                false
            )

        binding.viewModel = alertDialogViewModel

        alertDialogViewModel.navigationLiveData.observe(this, { myClass ->
            myClass?.let {
                val intent = Intent(requireContext(), myClass)
                intent.putExtra("selectedUsers", alertDialogViewModel.credentials)
                intent.putExtra("groupName", alertDialogViewModel.groupName.get())
                startActivity(intent)
                alertDialogViewModel.navigationLiveData.value = null
            }
        })

        alertDialog.setView(binding.root)

        return alertDialog.create()
    }
}