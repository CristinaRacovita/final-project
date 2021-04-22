package com.example.moviepicker.presentation.fragment

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.work.WorkManager
import com.example.moviepicker.R
import com.example.moviepicker.databinding.FragmentOptionsBinding
import com.example.moviepicker.presentation.viewModelFactory.OptionsViewModelFactory
import com.example.moviepicker.presentation.viewmodel.OptionsViewModel


class OptionsFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences;

    private val requestCodeGallery = 1000

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
                OptionsViewModelFactory(
                    sharedPreferences,
                    WorkManager.getInstance(requireActivity())
                )
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

        optionsViewModel.changeImage.observe(requireActivity(), { changing ->
            changing?.let {
                val openGalleryIntent =
                    Intent(
                        Intent.ACTION_OPEN_DOCUMENT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                requireActivity().startActivityFromFragment(
                    this, openGalleryIntent,
                    requestCodeGallery
                )

                optionsViewModel.changeImage.value = null
            }
        })

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == requestCodeGallery) {
            val imageUri: Uri? = data?.data
            sharedPreferences.edit().putString("profile", imageUri.toString()).apply()

            val action: NavDirections = OptionsFragmentDirections.actionSettingsActionSelf()
            findNavController().navigate(action)
        }
    }
}