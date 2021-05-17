package com.example.moviepicker.domain.useCase

import androidx.lifecycle.MutableLiveData
import com.example.moviepicker.domain.items.ImageItem
import com.example.moviepicker.domain.mediator.UserMediator

class GetPhotoUseCase(private val mediator: UserMediator) {
    fun getPhoto(id: Int): MutableLiveData<ImageItem> {
        return mediator.getProfileImage(id)
    }
}