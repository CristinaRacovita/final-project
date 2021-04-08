package com.example.moviepicker.presentation.binderAdapter

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.presentation.adapter.CreateGroupAdapter
import com.example.moviepicker.presentation.viewmodel.GroupItemViewModel
import java.util.*

class GroupMenuBinderAdapter {
    companion object {
        @BindingAdapter("items", "progressBar")
        @JvmStatic
        fun setItems(recyclerView: RecyclerView, groupItems: List<GroupItemViewModel>, progressBar: ProgressBar) {
            var adapter = recyclerView.adapter
            if (adapter == null && groupItems.isNotEmpty()) {
                adapter = CreateGroupAdapter(groupItems)
                recyclerView.adapter = adapter
                val layoutManager: RecyclerView.LayoutManager =
                    GridLayoutManager(recyclerView.context, 2)
                recyclerView.layoutManager = layoutManager
            }

            changeProgressBarVisibility(groupItems, progressBar)
        }

        @BindingAdapter("allItems", "filter", "progressBar1")
        @JvmStatic
        fun updateItems(
            recyclerView: RecyclerView,
            allGroupItems: List<GroupItemViewModel>,
            filter: String?,
            progressBar1: ProgressBar
        ) {
            changeProgressBarVisibility(allGroupItems, progressBar1)

            if (filter != "" && filter != null && recyclerView.adapter != null) {
                val adapter = recyclerView.adapter as CreateGroupAdapter
                val newGroupItems: MutableList<GroupItemViewModel> = ArrayList()
                for (user: GroupItemViewModel in allGroupItems) {
                    if (user.username.get()!!.contains(filter)) {
                        newGroupItems.add(user)
                    }
                }
                changeProgressBarVisibility(newGroupItems, progressBar1)

                adapter.updateItems(newGroupItems)
            }

            if (filter == "" && recyclerView.adapter != null) {
                val adapter = recyclerView.adapter as CreateGroupAdapter
                adapter.updateItems(allGroupItems)
            }
        }

        private fun changeProgressBarVisibility(groupItems: List<GroupItemViewModel>, progressBar: ProgressBar) {
            if (groupItems.isEmpty()) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }
}