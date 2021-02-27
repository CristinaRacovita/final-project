package com.example.moviepicker.presentation.binderAdapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.domain.UserItem
import com.example.moviepicker.presentation.adapter.CreateGroupAdapter

class GroupMenuBinderAdapter {

    companion object {
        @BindingAdapter("items")
        @JvmStatic
        fun setItems(recyclerView: RecyclerView, items: List<UserItem>) {
            var adapter = recyclerView.adapter
            if (adapter == null && items.isNotEmpty()) {
                adapter = CreateGroupAdapter(items)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            }
        }
    }
}