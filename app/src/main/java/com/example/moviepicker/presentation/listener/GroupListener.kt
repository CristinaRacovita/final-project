package com.example.moviepicker.presentation.listener

import com.example.moviepicker.domain.items.AllGroupsItem

interface GroupListener {
    fun goToGroup(allGroupsItem: AllGroupsItem)
}