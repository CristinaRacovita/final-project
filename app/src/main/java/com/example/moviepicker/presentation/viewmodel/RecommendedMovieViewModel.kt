package com.example.moviepicker.presentation.viewmodel

import android.content.SharedPreferences
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkManager
import com.example.moviepicker.domain.items.GroupMovieItem
import com.example.moviepicker.domain.items.RatingItem
import com.example.moviepicker.domain.items.RecommendedMovieItem
import com.example.moviepicker.domain.useCase.AddRatingsUseCase
import com.example.moviepicker.domain.useCase.GetRecommendedMovieUseCase
import com.example.moviepicker.domain.useCase.GroupUseCase
import com.example.moviepicker.domain.workers.WatchedWorker
import com.example.moviepicker.presentation.listener.WatchedMovieListener

class RecommendedMovieViewModel(
    val sharedPreferences: SharedPreferences,
    getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
    private val workManager: WorkManager,
    genre: String?,
    year: String?,
    private val type: Int,
    private var ids: String?,
    private val groupId: Int,
    private val addRatingsUseCase: AddRatingsUseCase,
    private val groupUseCase: GroupUseCase
) : ViewModel(), WatchedMovieListener {
    var recommendedMovies: ObservableArrayList<RecommendedMovieItem> = ObservableArrayList()
    var closedLiveData = MutableLiveData<Boolean>()

    init {
        val currentUserId = sharedPreferences.getInt("id", -1)
        val liveRecommendation: LiveData<List<RecommendedMovieItem>> = if (type == 1) {
            getRecommendedMovieUseCase.getRecommended(currentUserId, genre, year)
        } else {
            if (!ids!!.contains(currentUserId.toString())) {
                ids += "$currentUserId-"
            }

            getRecommendedMovieUseCase.getGroupRecommended(ids!!)
        }

        liveRecommendation.observeForever { movies: List<RecommendedMovieItem>? ->
            if (movies != null) {
                recommendedMovies.addAll(movies)
            }

        }
    }

    override fun watchedMovie(recommendedMovieItem: RecommendedMovieItem) {
        if (type == 1) {
            WatchedWorker.startWorker(
                recommendedMovieItem.title,
                recommendedMovieItem.id,
                workManager
            )
        } else {
            groupUseCase.addGroupMovie(GroupMovieItem(groupId, recommendedMovieItem.id))
            val ratings: MutableList<RatingItem> = ArrayList()

            if (ids != null) {
                for (id in ids!!.split("-")) {
                    if (id != "") {
                        ratings.add(RatingItem(id.toInt(), recommendedMovieItem.id, null))
                    }
                }
            }

            addRatingsUseCase.addRatings(ratings)
        }

        closedLiveData.value = true
    }
}