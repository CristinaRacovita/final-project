package com.example.moviepicker.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.RemoteDataSource
import com.example.moviepicker.databinding.GroupMenuBinding
import com.example.moviepicker.domain.UserMediator
import com.example.moviepicker.domain.useCase.FetchCredentialsUseCase
import com.example.moviepicker.presentation.viewModelFactory.AlertDialogViewModelFactory
import com.example.moviepicker.presentation.viewmodel.AlertDialogViewModel
import com.example.moviepicker.presentation.viewmodel.RegisterViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun logout(view: View) {
        val sharedPreferences =
            this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(RegisterViewModel.auth_tag, false).apply()

        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    fun createGroup(view: View) {
        val alertDialog = AlertDialog.Builder(this)

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

        val binding: GroupMenuBinding = DataBindingUtil.setContentView(this, R.layout.group_menu)

        binding.viewModel = alertDialogViewModel

        var parentView: ViewParent? = null

        if (binding.root.parent != null) {
            parentView = binding.root.parent
            val viewGroup = binding.root.parent as ViewGroup
            viewGroup.removeView(binding.root)
        }

        alertDialog.setView(binding.root)

        val dialog = alertDialog.create()

        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)

        val buttonCancel = binding.close

        buttonCancel.setOnClickListener {
            if(binding.root.parent == null) {
                val viewGroup = parentView as ViewGroup
                viewGroup.addView(binding.root)
                dialog.dismiss()
            }

        }

        dialog.show()

    }

}