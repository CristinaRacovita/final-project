package com.example.moviepicker.presentation.binderAdapter

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.domain.items.UserDetailsItem
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
    }
}