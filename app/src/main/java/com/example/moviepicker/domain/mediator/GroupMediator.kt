package com.example.moviepicker.domain.mediator

import androidx.lifecycle.MutableLiveData
import com.example.moviepicker.domain.builder.AllGroupsBuilder
import com.example.moviepicker.domain.builder.GroupBuilder
import com.example.moviepicker.domain.builder.GroupMovieBuilder
import com.example.moviepicker.domain.builder.GroupUserBuilder
import com.example.moviepicker.domain.items.AllGroupsItem
import com.example.moviepicker.domain.items.GroupItem
import com.example.moviepicker.domain.items.GroupMovieItem
import com.example.moviepicker.domain.items.GroupUserItem
import com.example.moviepicker.domain.repository.GroupRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.stream.Collectors

class GroupMediator(private val remoteRepository: GroupRepository) {
    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private var liveGroup: MutableLiveData<GroupItem> = MutableLiveData()
    private var livegroups: MutableLiveData<List<AllGroupsItem>> = MutableLiveData()

    fun createGroup(group: GroupItem): MutableLiveData<GroupItem> {
        executorService.execute {
            liveGroup.postValue(
                GroupBuilder.toItem(
                    remoteRepository.createGroup(
                        GroupBuilder.toDTO(
                            group
                        )
                    )
                )
            )
        }

        return liveGroup
    }

    fun addMembers(groupUsers: List<GroupUserItem>) {
        executorService.execute {
            remoteRepository.addMembers(
                groupUsers.stream().map(GroupUserBuilder::toDTO).collect(Collectors.toList())
            )
        }
    }

    fun getGroups(id: Int): MutableLiveData<List<AllGroupsItem>> {
        executorService.execute {
            livegroups.postValue(
                remoteRepository.getGroups(id).stream()
                    .map(AllGroupsBuilder::toItem)
                    .collect(
                        Collectors.toList()
                    )
            )
        }

        return livegroups
    }

    fun addGroupMovie(groupMovieItem: GroupMovieItem) {
        executorService.execute {
            remoteRepository.addGroupMovie(
                GroupMovieBuilder.toDTO(groupMovieItem)
            )
        }
    }
}