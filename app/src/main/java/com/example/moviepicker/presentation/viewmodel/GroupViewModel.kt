package com.example.moviepicker.presentation.viewmodel

import android.os.Bundle
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.items.UserDetailsItem
import com.example.moviepicker.domain.useCase.FetchDisplayMovieUseCase
import com.example.moviepicker.domain.useCase.FetchUserDetailsUseCase
import com.example.moviepicker.presentation.dialog.RecommendationDialog

class GroupViewModel(
    val title: String,
    fetchUserDetailsUseCase: FetchUserDetailsUseCase,
    private val flag: Boolean,
    private val receivedUsers: List<Any>,
    private val fragmentManager: FragmentManager,
    private val groupId: Int,
    fetchDisplayMovieUseCase: FetchDisplayMovieUseCase
) :
    ViewModel() {
    var users: ObservableArrayList<UserDetailsItem> = ObservableArrayList()
    var groupMovies: ObservableArrayList<DisplayMovieItem> = ObservableArrayList()
    var navigationLiveData = MutableLiveData<Class<*>>()

    init {
        if (flag) {
            this.users.addAll(
                receivedUsers as List<UserDetailsItem>
            )

        } else {
            var ids = ""

            receivedUsers as List<GroupItemViewModel>
            for (user in receivedUsers) {
                ids += user.userId.get().toString() + "-"
            }

            val usersLive: LiveData<List<UserDetailsItem>> =
                fetchUserDetailsUseCase.getUsersDetails(ids)

            usersLive.observeForever { items: List<UserDetailsItem?>? ->
                if (items != null) {
                    this.users.addAll(
                        items
                    )
                }

            }

        }

        val groupMoviesLive: LiveData<List<DisplayMovieItem>> =
            fetchDisplayMovieUseCase.getGroupMovies(groupId)

        groupMoviesLive.observeForever { items: List<DisplayMovieItem?>? ->
            if (items != null) {
                this.groupMovies.addAll(
                    items
                )
            }

        }

    }

    fun getRecommendation() {
        var ids = ""

        val recommendationDialog: DialogFragment = RecommendationDialog()
        val args = Bundle()

        if (flag) {
            receivedUsers as List<UserDetailsItem>
            for (user in receivedUsers) {
                ids += user.id.toString() + "-"
            }

        } else {
            receivedUsers as List<GroupItemViewModel>
            for (user in receivedUsers) {
                ids += user.userId.get().toString() + "-"
            }
        }

        args.putString("ids", ids)
        args.putInt("groupId", groupId)
        recommendationDialog.arguments = args
        recommendationDialog.show(fragmentManager, "group_recommendation")
    }

}