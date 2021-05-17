package com.example.moviepicker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.moviepicker.domain.mediator.UserMediator
import okhttp3.MultipartBody

class UploadPhotoUseCase(private val mediator: UserMediator) {
    fun uploadPhoto(id: Int, photo: MultipartBody.Part): LiveData<String> {
        return mediator.uploadPhoto(id, photo)
    }
}