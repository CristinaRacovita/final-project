package com.example.moviepicker.domain.mediator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviepicker.domain.builder.UserBuilder
import com.example.moviepicker.domain.builder.UserDetailsBuilder
import com.example.moviepicker.domain.items.ImageItem
import com.example.moviepicker.domain.items.UserDetailsItem
import com.example.moviepicker.domain.items.UserItem
import com.example.moviepicker.domain.repository.UserRepository
import okhttp3.MultipartBody
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.stream.Collectors

class UserMediator(private val remoteRepository: UserRepository) {
    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private var credentials: MutableLiveData<List<UserDetailsItem>> = MutableLiveData()
    private var user: MutableLiveData<UserItem> = MutableLiveData()
    private var profileImage: MutableLiveData<ImageItem> = MutableLiveData()
    private var message: MutableLiveData<String> = MutableLiveData()
    private var details: MutableLiveData<List<UserDetailsItem>> = MutableLiveData()
    private var users: MutableLiveData<List<UserItem>> = MutableLiveData()

    fun getCredentials(): LiveData<List<UserDetailsItem>> {
        executorService.execute {
            credentials.postValue(
                remoteRepository.getCredentials().stream().map(UserDetailsBuilder::toItem).collect(
                    Collectors.toList()
                )
            )
        }

        return credentials
    }

    fun getUsers(): LiveData<List<UserItem>> {
        executorService.execute {
            users.postValue(
                remoteRepository.getUsers().stream().map(UserBuilder::toItem).collect(
                    Collectors.toList()
                )
            )
        }

        return users
    }

    fun createNewUser(userItem: UserItem): LiveData<UserItem> {
        executorService.execute {
            user.postValue(
                UserBuilder.toItem(
                    remoteRepository.createNewUser(
                        UserBuilder.toDTO(
                            userItem
                        )
                    )
                )
            )
        }

        return user
    }

    fun uploadPhoto(id: Int, photo: MultipartBody.Part): LiveData<String> {
        executorService.execute {
            message.postValue(remoteRepository.uploadPhoto(id, photo))
        }

        return message
    }

    fun getProfileImage(id: Int): MutableLiveData<ImageItem> {
        executorService.execute {
            profileImage.postValue(ImageItem(remoteRepository.getProfileImage(id).profileImage))
        }

        return profileImage
    }

    fun getUsersDetails(ids: String): MutableLiveData<List<UserDetailsItem>> {
        executorService.execute {
            details.postValue(
                remoteRepository.getUsersDetails(ids).stream().map(UserDetailsBuilder::toItem)
                    .collect(
                        Collectors.toList()
                    )
            )
        }

        return details
    }
}