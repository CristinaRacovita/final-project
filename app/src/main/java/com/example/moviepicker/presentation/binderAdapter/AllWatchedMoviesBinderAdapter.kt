package com.example.moviepicker.presentation.binderAdapter

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.domain.items.WatchedMovieItem
import com.example.moviepicker.presentation.adapter.AllWatchedMoviesAdapter

class AllWatchedMoviesBinderAdapter {
    companion object {
        @BindingAdapter("watched_movies", "progressBarWatched")
        @JvmStatic
        fun setItems(
            recyclerView: RecyclerView,
            items: List<WatchedMovieItem>?,
            progressBarWatched: ProgressBar
        ) {
            var adapter = recyclerView.adapter
            if (adapter == null) {
                adapter = AllWatchedMoviesAdapter()
                recyclerView.adapter = adapter
                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(recyclerView.context)
                recyclerView.layoutManager = layoutManager
            }
            if (items != null) {
                adapter as AllWatchedMoviesAdapter
                adapter.updateItems(items)

                changeProgressBarVisibility(items, progressBarWatched)
            }
        }

        fun changeProgressBarVisibility(items: List<Any>, progressBar: ProgressBar) {
            if (items.isEmpty()) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }

    }
}