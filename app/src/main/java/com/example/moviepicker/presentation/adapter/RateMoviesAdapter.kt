package com.example.moviepicker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.R
import com.example.moviepicker.databinding.ItemMovieRatedBinding
import com.example.moviepicker.presentation.listener.RateListener
import com.example.moviepicker.presentation.viewmodel.PickedMovieItemViewModel

class RateMoviesAdapter : RecyclerView.Adapter<RateMoviesAdapter.RateMoviesViewHolder>() {
    private lateinit var movies: List<PickedMovieItemViewModel>
    private lateinit var listener: RateListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RateMoviesViewHolder {
        val binding: ItemMovieRatedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_rated,
            parent,
            false
        )

        return RateMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RateMoviesViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateItems(
        movies: List<PickedMovieItemViewModel>,

        ) {
        this.movies = movies
        notifyDataSetChanged()
    }

    fun updateListener(
        rateListener: RateListener
    ) {
        this.listener = rateListener
        notifyDataSetChanged()
    }

    class RateMoviesViewHolder(private val binding: ItemMovieRatedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PickedMovieItemViewModel, rateListener: RateListener) {
            binding.model = item
            binding.rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                rateListener.rateMovie(
                    ratingBar,
                    item
                )
            }
        }

    }
}