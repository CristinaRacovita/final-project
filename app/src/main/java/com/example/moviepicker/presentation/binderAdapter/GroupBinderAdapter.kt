package com.example.moviepicker.presentation.binderAdapter

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.domain.items.DisplayMovieItem
import com.example.moviepicker.domain.items.UserDetailsItem
import com.example.moviepicker.presentation.adapter.MovieGroupAdapter
import com.example.moviepicker.presentation.adapter.UsersAdapter

class GroupBinderAdapter {
    companion object {
        @BindingAdapter("title")
        @JvmStatic
        fun setTitle(toolbar: Toolbar, title: String) {
            toolbar.title = title
        }

        @BindingAdapter("users")
        @JvmStatic
        fun setUsers(
            recyclerView: RecyclerView,
            users: List<UserDetailsItem>?,
        ) {
            var adapter = recyclerView.adapter
            if (adapter == null && users?.isNotEmpty()!!) {
                adapter = UsersAdapter()
                recyclerView.adapter = adapter
                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
                recyclerView.layoutManager = layoutManager
            }
            if (users != null && adapter != null) {
                val myAdapter = adapter as UsersAdapter
                myAdapter.update(users)
            }

        }

        @BindingAdapter("group_movies", "textView")
        @JvmStatic
        fun setMoviesGroup(
            recyclerView: RecyclerView,
            group_movies: List<DisplayMovieItem>?,
            textView: TextView
        ) {
            var adapter = recyclerView.adapter
            if (adapter == null && group_movies?.isNotEmpty()!!) {
                adapter = MovieGroupAdapter()
                recyclerView.adapter = adapter
                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(recyclerView.context)
                recyclerView.layoutManager = layoutManager
            }
            if (group_movies != null && adapter != null) {
                val myAdapter = adapter as MovieGroupAdapter
                myAdapter.update(group_movies)
            }

            changeTextViewVisibility(group_movies, textView, recyclerView)
        }

        private fun changeTextViewVisibility(
            group_movies: List<DisplayMovieItem>?,
            textView: TextView,
            recyclerView: RecyclerView
        ) {
            if (group_movies == null || group_movies.isEmpty()) {
                textView.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                textView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }
}