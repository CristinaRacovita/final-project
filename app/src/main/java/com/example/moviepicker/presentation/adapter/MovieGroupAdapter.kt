package com.example.moviepicker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.R
import com.example.moviepicker.databinding.ItemMovieGroupBinding
import com.example.moviepicker.domain.items.DisplayMovieItem

class MovieGroupAdapter :
    RecyclerView.Adapter<MovieGroupAdapter.MovieGroupViewHolder>() {
    private lateinit var movies: List<DisplayMovieItem>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieGroupViewHolder {
        val binding: ItemMovieGroupBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_group,
            parent,
            false
        )

        return MovieGroupViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieGroupViewHolder,
        position: Int
    ) {
        val item = movies[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun update(
        movies: List<DisplayMovieItem>,
    ) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class MovieGroupViewHolder(private val binding: ItemMovieGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DisplayMovieItem) {
            binding.movieItemViewModel = item
        }
    }
}