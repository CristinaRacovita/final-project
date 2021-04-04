package com.example.moviepicker.domain.mediator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviepicker.domain.repository.UserRepository
import com.example.moviepicker.domain.builder.UserBuilder
import com.example.moviepicker.domain.items.UserItem
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

    fun createNewUser(userItem: UserItem) {
        executorService.execute {
            remoteRepository.createNewUser(UserBuilder.toDTO(userItem))
        }
    }
}