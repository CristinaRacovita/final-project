package com.example.moviepicker.presentation.binderAdapter

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.domain.items.AllGroupsItem
import com.example.moviepicker.presentation.adapter.AllGroupsAdapter
import com.example.moviepicker.presentation.listener.GroupListener

class AllGroupsBinderAdapter {
    companion object {
        @BindingAdapter("groupsItems", "progressBarGroup", "groupListener")
        @JvmStatic
        fun setGroups(
            myRecyclerView: RecyclerView,
            groupsItems: List<AllGroupsItem>?,
            progressBarGroup: ProgressBar,
            groupListener: GroupListener?
        ) {
            var adapter = myRecyclerView.adapter
            if (adapter == null) {
                adapter = AllGroupsAdapter()
                myRecyclerView.adapter = adapter
                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(myRecyclerView.context)
                myRecyclerView.layoutManager = layoutManager
            }

            if (groupsItems != null) {
                adapter as AllGroupsAdapter
                adapter.updateGroups(groupsItems)

                AllWatchedMoviesBinderAdapter.changeProgressBarVisibility(
                    groupsItems,
                    progressBarGroup
                )
            }

            if (groupListener != null) {
                adapter as AllGroupsAdapter
                adapter.updateListener(groupListener)
            }
        }

    }
}