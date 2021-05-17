package com.example.moviepicker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.R
import com.example.moviepicker.databinding.ItemUserProfileBinding
import com.example.moviepicker.domain.items.UserDetailsItem

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    private lateinit var users: List<UserDetailsItem>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersAdapter.UsersViewHolder {
        val binding: ItemUserProfileBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user_profile,
            parent,
            false
        )

        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersAdapter.UsersViewHolder, position: Int) {
        val item = users[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun update(
        users: List<UserDetailsItem>,
    ) {
        this.users = users
        notifyDataSetChanged()
    }

    class UsersViewHolder(private val binding: ItemUserProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserDetailsItem) {
            binding.model = item
        }
    }
}