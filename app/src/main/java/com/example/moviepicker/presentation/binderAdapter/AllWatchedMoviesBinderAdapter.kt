package com.example.moviepicker.presentation.binderAdapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.domain.items.WatchedMovieItem
import com.example.moviepicker.presentation.adapter.AllWatchedMoviesAdapter

class AllWatchedMoviesBinderAdapter {
    companion object {
        @BindingAdapter("watched_movies")
        @JvmStatic
        fun setItems(
            recyclerView: RecyclerView,
            items: List<WatchedMovieItem>?,
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
            }
        }

    }
}