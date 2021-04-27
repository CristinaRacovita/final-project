package com.example.moviepicker.domain.repository

import com.example.moviepicker.data.dtos.GroupDTO
import com.example.moviepicker.data.dtos.GroupUserDTO

interface GroupRepository {
    fun createGroup(group: GroupDTO): GroupDTO
    fun addMembers(groupUsers: List<GroupUserDTO>): List<GroupUserDTO>
    fun getGroups(id: Int): List<GroupDTO>
}