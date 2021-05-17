package com.example.moviepicker.data.remote

import com.example.moviepicker.data.dtos.AllGroupsDTO
import com.example.moviepicker.data.dtos.GroupDTO
import com.example.moviepicker.data.dtos.GroupMovieDTO
import com.example.moviepicker.data.dtos.GroupUserDTO
import com.example.moviepicker.domain.repository.GroupRepository

class GroupRemoteDataSource(private val api: MoviePickerAPI) : GroupRepository {
    override fun createGroup(group: GroupDTO): GroupDTO {
        return api.createGroup(group).execute().body()!!
    }

    override fun addMembers(groupUsers: List<GroupUserDTO>): List<GroupUserDTO> {
        return api.addMembers(groupUsers).execute().body()!!
    }

    override fun getGroups(id: Int): List<AllGroupsDTO> {
        val groups = api.getGroups(id).execute().body()
        if (groups != null) {
            return groups
        }

        throw NullPointerException("Expression 'api.getGroups(id).execute().body()' must not be null")
    }

    override fun addGroupMovie(groupMovie: GroupMovieDTO) {
        api.addGroupMovie(groupMovie).execute().body()!!
    }
}