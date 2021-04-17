package com.example.moviepicker.presentation.binderAdapter

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.domain.items.RecommendedMovieItem
import com.example.moviepicker.presentation.adapter.RecommendationMovieAdapter
import com.example.moviepicker.presentation.dialog.CreateGroupAlertDialog
import com.example.moviepicker.presentation.dialog.RecommendationDialog
import com.example.moviepicker.presentation.listener.WatchedMovieListener

class HomeBinderAdapter {
    companion object {
        @BindingAdapter("fragmentManager")
        @JvmStatic
        fun createWatchParty(linearLayout: LinearLayout, fragmentManager: FragmentManager) {
            linearLayout.setOnClickListener {
                val createGroupDialogFragment: DialogFragment = CreateGroupAlertDialog()
                createGroupDialogFragment.show(fragmentManager, "createWatchParty")
            }
        }

        @BindingAdapter("items", "listener")
        @JvmStatic
        fun setItems(
            recyclerView: RecyclerView,
            items: List<RecommendedMovieItem>?,
            listener: WatchedMovieListener?
        ) {
            var adapter = recyclerView.adapter
            if (adapter == null) {
                adapter = RecommendationMovieAdapter()
                recyclerView.adapter = adapter
                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(recyclerView.context,
                        LinearLayoutManager.HORIZONTAL, false)
                recyclerView.layoutManager = layoutManager
            }
            if (items != null) {
                adapter as RecommendationMovieAdapter
                adapter.updateItems(items)
            }
            if (listener != null) {
                adapter as RecommendationMovieAdapter
                adapter.updateListener(listener)
            }
        }

    }
}