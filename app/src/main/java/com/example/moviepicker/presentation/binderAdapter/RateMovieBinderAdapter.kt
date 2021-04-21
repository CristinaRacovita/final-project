package com.example.moviepicker.presentation.binderAdapter

import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepicker.R
import com.example.moviepicker.domain.items.RecommendedMovieItem
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

        @BindingAdapter("rated_movies", "listener", "progressBarRate")
        @JvmStatic
        fun setItems(
            recyclerView: RecyclerView,
            items: List<PickedMovieItemViewModel>?,
            rateListener: RateListener?,
            progressBarRate: ProgressBar
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
                GroupMenuBinderAdapter.changeProgressBarVisibility(items, progressBarRate)
            }

            if (items != null && rateListener != null) {
                adapter as RateMoviesAdapter
                adapter.updateListener(rateListener)
            }
        }

        @BindingAdapter("my_rated_movies", "watched")
        @JvmStatic
        fun checkIfRated(button: Button, numberOfMovies: Int, totalMovies: Int) {
            button.isEnabled = numberOfMovies >= totalMovies
            button.isClickable = numberOfMovies >= totalMovies
        }

        @BindingAdapter("recommended_movie")
        @JvmStatic
        fun setText(textView: TextView, movie: RecommendedMovieItem?) {
            if (textView.context.getString(R.string.lang) == "en") {
                textView.text = movie?.descriptionEn
            } else {
                textView.text = movie?.descriptionRo
            }
        }

    }
}