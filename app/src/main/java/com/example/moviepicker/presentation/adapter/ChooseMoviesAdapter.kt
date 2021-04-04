package com.example.moviepicker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.R
import com.example.moviepicker.databinding.ItemMovieChoosenBinding
import com.example.moviepicker.presentation.listener.DisplayMovieListener
import com.example.moviepicker.presentation.viewmodel.DisplayMovieItemViewModel

class ChooseMoviesAdapter:
    RecyclerView.Adapter<ChooseMoviesAdapter.ChooseMoviesViewHolder>() {
    private lateinit var displayMovieListener: DisplayMovieListener
    private lateinit var movies: List<DisplayMovieItemViewModel>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChooseMoviesViewHolder {
        val binding: ItemMovieChoosenBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_choosen,
            parent,
            false
        )

        return ChooseMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ChooseMoviesViewHolder,
        position: Int
    ) {
        val item = movies[position]
        holder.bind(item, displayMovieListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateItems(
        movies: List<DisplayMovieItemViewModel>,
        displayMovieListener: DisplayMovieListener
    ) {
        this.movies = movies
        this.displayMovieListener = displayMovieListener
        notifyDataSetChanged()
    }

    fun update(
        movies: List<DisplayMovieItemViewModel>,
    ) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class ChooseMoviesViewHolder(private val binding: ItemMovieChoosenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DisplayMovieItemViewModel, listener: DisplayMovieListener) {
            binding.movieItemViewModel = item
            binding.listener = listener
        }
    }
}