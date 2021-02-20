package com.example.moviepicker.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.stream.Collectors

class UserMediator(private val remoteRepository: UserRepository) {
    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private var credentials: MutableLiveData<List<UserItem>> = MutableLiveData()


    fun getCredentials(): LiveData<List<UserItem>> {
        executorService.execute {
            credentials.postValue(
                remoteRepository.getCredentials().stream().map(UserBuilder::toItem).collect(
                    Collectors.toList()
                )
            )
        }

        return credentials
    }

    fun createNewUser(number: Int, userItem: UserItem) {
        executorService.execute {
            remoteRepository.createNewUser(number, UserBuilder.toDTO(userItem))
        }
    }
}