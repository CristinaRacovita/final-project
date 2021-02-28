package com.example.moviepicker.presentation.binderAdapter

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.domain.UserItem
import com.example.moviepicker.presentation.adapter.CreateGroupAdapter
import com.example.moviepicker.presentation.viewmodel.ItemViewModel
import java.util.*

class GroupMenuBinderAdapter {
    companion object {
        @BindingAdapter("items", "progressBar")
        @JvmStatic
        fun setItems(recyclerView: RecyclerView, items: List<ItemViewModel>, progressBar: ProgressBar) {
            var adapter = recyclerView.adapter
            if (adapter == null && items.isNotEmpty()) {
                adapter = CreateGroupAdapter(items)
                recyclerView.adapter = adapter
                val layoutManager: RecyclerView.LayoutManager =
                    GridLayoutManager(recyclerView.context, 2)
                recyclerView.layoutManager = layoutManager
            }

            changeProgressBarVisibility(items, progressBar)
        }

        @BindingAdapter("allItems", "filter", "progressBar1")
        @JvmStatic
        fun updateItems(
            recyclerView: RecyclerView,
            allItems: List<ItemViewModel>,
            filter: String?,
            progressBar1: ProgressBar
        ) {
            changeProgressBarVisibility(allItems, progressBar1)

            if (filter != "" && filter != null && recyclerView.adapter != null) {
                val adapter = recyclerView.adapter as CreateGroupAdapter
                val newItems: MutableList<ItemViewModel> = ArrayList()
                for (user: ItemViewModel in allItems) {
                    if (user.username.get()!!.contains(filter)) {
                        newItems.add(user)
                    }
                }
                changeProgressBarVisibility(newItems, progressBar1)

                adapter.updateItems(newItems)
            }

            if (filter == "" && recyclerView.adapter != null) {
                val adapter = recyclerView.adapter as CreateGroupAdapter
                adapter.updateItems(allItems)
            }
        }

        private fun changeProgressBarVisibility(items: List<ItemViewModel>, progressBar: ProgressBar) {
            if (items.isEmpty()) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }
}