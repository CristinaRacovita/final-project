package com.example.moviepicker.presentation.binderAdapter

import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepicker.presentation.adapter.RateMoviesAdapter
import com.example.moviepicker.presentation.listener.RateListener
import com.example.moviepicker.presentation.viewmodel.PickedMovieItemViewModel

class RateMovieBinderAdapter {
    companion object {
        @BindingAdapter("image_url")
        @JvmStatic
        fun loadImage(imageView: ImageView, imageUrl: String?) {
            if (imageUrl != null && imageUrl != "") {
                Glide.with(imageView.context)
                    .load(imageUrl)
                    .into(imageView)
            }
        }

        @BindingAdapter("rated_movies", "listener")
        @JvmStatic
        fun setItems(
            recyclerView: RecyclerView,
            items: List<PickedMovieItemViewModel>?,
            rateListener: RateListener?
        ) {
            var adapter = recyclerView.adapter
            if (adapter == null) {
                adapter = RateMoviesAdapter()
                recyclerView.adapter = adapter
                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
                recyclerView.layoutManager = layoutManager
            }
            if (items != null) {
                adapter as RateMoviesAdapter
                adapter.updateItems(items)
            }

            if (items != null && rateListener != null) {
                adapter as RateMoviesAdapter
                adapter.updateListener(rateListener)
            }
        }

        @BindingAdapter("my_rated_movies")
        @JvmStatic
        fun checkIfRated(button: Button, numberOfMovies: Int) {
            button.isEnabled = numberOfMovies >= 5
            button.isClickable = numberOfMovies >= 5
        }

    }
}