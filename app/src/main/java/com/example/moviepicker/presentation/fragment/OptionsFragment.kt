package com.example.moviepicker.presentation.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.work.WorkManager
import com.example.moviepicker.R
import com.example.moviepicker.data.remote.MoviePickerAPI
import com.example.moviepicker.data.remote.UserRemoteDataSource
import com.example.moviepicker.databinding.FragmentOptionsBinding
import com.example.moviepicker.domain.mediator.UserMediator
import com.example.moviepicker.domain.useCase.GetPhotoUseCase
import com.example.moviepicker.domain.useCase.UploadPhotoUseCase
import com.example.moviepicker.domain.utils.FileUtil
import com.example.moviepicker.presentation.viewModelFactory.OptionsViewModelFactory
import com.example.moviepicker.presentation.viewmodel.OptionsViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class OptionsFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences;
    lateinit var imageUri: Uri
    private val requestCodeGallery = 1000
    lateinit var userRemoteDataSource: UserRemoteDataSource
    lateinit var userMediator: UserMediator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreferences =
            requireActivity().getSharedPreferences(
                getString(R.string.preference_file_key),
                AppCompatActivity.MODE_PRIVATE
            )

        userRemoteDataSource = UserRemoteDataSource(MoviePickerAPI.createAPI())
        userMediator = UserMediator(userRemoteDataSource)
        val getPhotoUseCase = GetPhotoUseCase(userMediator)

        val optionsViewModel =
            ViewModelProvider(
                this,
                OptionsViewModelFactory(
                    sharedPreferences,
                    WorkManager.getInstance(requireActivity()),
                    getPhotoUseCase
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
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
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
            imageUri = data?.data!!

            requestRead()
        }
    }

    private fun uploadProfileImage(imageUri: Uri) {
        sharedPreferences.edit().putString("profile", imageUri.toString()).apply()
        val currentUserId = sharedPreferences.getInt("id", -1)

        val uploadPhotoUseCase = UploadPhotoUseCase(userMediator)

        val imageFile = File(FileUtil.getPath(requireContext(), imageUri))
        val requestBody = imageFile.asRequestBody("multipart/data".toMediaTypeOrNull())
        val multiPart = MultipartBody.Part.createFormData("model_pic", imageFile.name, requestBody)

        val photoLive: LiveData<String> =
            uploadPhotoUseCase.uploadPhoto(currentUserId, multiPart)

        photoLive.observeForever { message: String? ->
            if (message != null) {
                val action: NavDirections = OptionsFragmentDirections.actionSettingsActionSelf()
                findNavController().navigate(action)
            }
        }
    }

    private fun requestRead() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
            uploadProfileImage(imageUri)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                uploadProfileImage(imageUri)
            } else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
            return
        }
    }
}