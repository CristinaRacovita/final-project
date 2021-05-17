package com.example.moviepicker.presentation.binderAdapter

import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.domain.items.RecommendedMovieItem
import com.example.moviepicker.presentation.adapter.RecommendationMovieAdapter
import com.example.moviepicker.presentation.dialog.CreateGroupAlertDialog
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

        @BindingAdapter("items", "listener", "progressBarRecommended", "textView")
        @JvmStatic
        fun setItems(
            recyclerView: RecyclerView,
            items: List<RecommendedMovieItem>?,
            listener: WatchedMovieListener?,
            progressBar: ProgressBar,
            textView: TextView
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
                AllWatchedMoviesBinderAdapter.changeProgressBarVisibility(items, progressBar)
                changeMessageVisibility(items, textView)
            }
            if (listener != null) {
                adapter as RecommendationMovieAdapter
                adapter.updateListener(listener)
            }
        }

        private fun changeMessageVisibility(items: List<Any>, textView: TextView) {
            if (items.isEmpty()) {
                textView.visibility = View.VISIBLE
            } else {
                textView.visibility = View.GONE
            }
        }

    }
}