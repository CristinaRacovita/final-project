package com.example.moviepicker.presentation.binderAdapter

import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.R
import com.example.moviepicker.presentation.adapter.ChooseMoviesAdapter
import com.example.moviepicker.presentation.listener.DisplayMovieListener
import com.example.moviepicker.presentation.viewmodel.DisplayMovieItemViewModel
import java.util.*


class ChooseMoviesBinderAdapter {
    companion object {
        @BindingAdapter("movies", "listener")
        @JvmStatic
        fun setItems(
            recyclerView: RecyclerView,
            items: List<DisplayMovieItemViewModel>?,
            displayMovieListener: DisplayMovieListener
        ) {
            var adapter = recyclerView.adapter
            if (adapter == null && items?.isNotEmpty()!!) {
                adapter = ChooseMoviesAdapter()
                recyclerView.adapter = adapter
                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(recyclerView.context)
                recyclerView.layoutManager = layoutManager
            }
            if (items != null && adapter != null) {
                val myAdapter = adapter as ChooseMoviesAdapter
                myAdapter.updateItems(items, displayMovieListener)
            }

        }

        @BindingAdapter("toggle")
        @JvmStatic
        fun pickMovie(frameLayout: FrameLayout, toggle: Boolean) {
            val context = frameLayout.context
            val textView: TextView = frameLayout.findViewById(R.id.title_textView)
            if (toggle) {
                frameLayout.setBackgroundColor(context.getColor(R.color.red))
                textView.setTextColor(context.getColor(R.color.white))
            } else {
                frameLayout.setBackgroundColor(context.getColor(R.color.grey))
                textView.setTextColor(context.getColor(R.color.black))
            }

        }

        @BindingAdapter("noMovies")
        @JvmStatic
        fun addMovie(textView: TextView, noMovies: Int) {
            val numberMovie: String =
                textView.context.getString(R.string.you_selected_x_movies, noMovies)
            textView.text = numberMovie
        }

        @BindingAdapter("noMovies")
        @JvmStatic
        fun goNext(button: Button, noMovies: Int) {
            button.isEnabled = noMovies == 5
        }

        @BindingAdapter("allItems", "filter")
        @JvmStatic
        fun updateItems(
            recyclerView: RecyclerView,
            allItems: List<DisplayMovieItemViewModel>,
            filter: String?,
        ) {
            if (filter != "" && filter != null && recyclerView.adapter != null) {
                val adapter = recyclerView.adapter as ChooseMoviesAdapter
                val newGroupItems: MutableList<DisplayMovieItemViewModel> = ArrayList()
                for (movie: DisplayMovieItemViewModel in allItems) {
                    if (movie.movieTitle.get()!!.contains(filter)) {
                        newGroupItems.add(movie)
                    }
                }
                adapter.update(newGroupItems)
            }

            if (filter == "" && recyclerView.adapter != null) {
                val adapter = recyclerView.adapter as ChooseMoviesAdapter
                adapter.update(allItems)
            }
        }
    }
}