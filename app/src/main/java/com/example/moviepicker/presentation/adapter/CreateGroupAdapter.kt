package com.example.moviepicker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.R
import com.example.moviepicker.databinding.MenuGroupItemBinding
import com.example.moviepicker.domain.UserItem
import com.example.moviepicker.presentation.viewmodel.ItemViewModel

class CreateGroupAdapter(private var items: List<ItemViewModel>) :
    RecyclerView.Adapter<CreateGroupAdapter.CreateGroupViewHolder>() {

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
        val maxSize = 4
        if (items.size <= maxSize)
            return items.size

        return maxSize
    }

    fun updateItems(users: List<ItemViewModel>){
        this.items = users
        notifyDataSetChanged()
    }

    class CreateGroupViewHolder(private val binding: MenuGroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemViewModel) {
            binding.model = item
        }
    }
}