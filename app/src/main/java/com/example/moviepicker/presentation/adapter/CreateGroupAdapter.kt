package com.example.moviepicker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.R
import com.example.moviepicker.databinding.MenuGroupItemBinding
import com.example.moviepicker.domain.UserItem

class CreateGroupAdapter(val items: List<UserItem>) :
    RecyclerView.Adapter<CreateGroupAdapter.CreateGroupViewHolder>() {

    init {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreateGroupViewHolder {
        val binding: MenuGroupItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.menu_group_item,
            parent,
            false
        )

        return CreateGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CreateGroupViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CreateGroupViewHolder(val binding: MenuGroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserItem) {
            binding.model = item
        }
    }
}