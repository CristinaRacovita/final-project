package com.example.moviepicker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.R
import com.example.moviepicker.databinding.ItemWatchedMovieBinding
import com.example.moviepicker.domain.items.WatchedMovieItem

class AllWatchedMoviesAdapter :
    RecyclerView.Adapter<AllWatchedMoviesAdapter.AllWatchedMoviesViewHolder>() {
    private lateinit var movies: List<WatchedMovieItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllWatchedMoviesViewHolder {
        val binding: ItemWatchedMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_watched_movie,
            parent,
            false
        )

        return AllWatchedMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllWatchedMoviesViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateItems(
        movies: List<WatchedMovieItem>,
    ) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class AllWatchedMoviesViewHolder(private val binding: ItemWatchedMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(watchedMovieItem: WatchedMovieItem) {
            binding.model = watchedMovieItem
        }
    }
}