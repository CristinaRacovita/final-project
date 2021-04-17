package com.example.moviepicker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.R
import com.example.moviepicker.databinding.ItemRecommendationBinding
import com.example.moviepicker.domain.items.RecommendedMovieItem
import com.example.moviepicker.presentation.listener.WatchedMovieListener

class RecommendationMovieAdapter :
    RecyclerView.Adapter<RecommendationMovieAdapter.RecommendationMovieViewHolder>() {
    private lateinit var movies: List<RecommendedMovieItem>
    private lateinit var listener: WatchedMovieListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendationMovieViewHolder {
        val binding: ItemRecommendationBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_recommendation,
            parent,
            false
        )

        return RecommendationMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendationMovieViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateItems(
        movies: List<RecommendedMovieItem>,

        ) {
        this.movies = movies
        notifyDataSetChanged()
    }

    fun updateListener(
        listener: WatchedMovieListener
    ) {
        this.listener = listener
        notifyDataSetChanged()
    }

    class RecommendationMovieViewHolder(private val binding: ItemRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            recommendedMovieItem: RecommendedMovieItem,
            listener: WatchedMovieListener
        ) {
            binding.model = recommendedMovieItem
            binding.listener = listener
        }
    }

}
