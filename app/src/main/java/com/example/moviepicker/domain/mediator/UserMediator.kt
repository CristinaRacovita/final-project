package com.example.moviepicker.domain.mediator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviepicker.domain.builder.UserBuilder
import com.example.moviepicker.domain.items.UserItem
import com.example.moviepicker.domain.repository.UserRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.stream.Collectors

class UserMediator(private val remoteRepository: UserRepository) {
    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private var credentials: MutableLiveData<List<UserItem>> = MutableLiveData()
    private var user: MutableLiveData<UserItem> = MutableLiveData()


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
}