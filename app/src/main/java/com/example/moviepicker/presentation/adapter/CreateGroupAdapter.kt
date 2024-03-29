package com.example.moviepicker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.R
import com.example.moviepicker.databinding.MenuGroupItemBinding
import com.example.moviepicker.presentation.listener.GroupItemListener
import com.example.moviepicker.presentation.viewmodel.GroupItemViewModel

class CreateGroupAdapter(private var groupItems: List<GroupItemViewModel>) :
    RecyclerView.Adapter<CreateGroupAdapter.CreateGroupViewHolder>() {

    private lateinit var listener: GroupItemListener

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
        val item = groupItems[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int {
        val maxSize = 4
        if (groupItems.size <= maxSize)
            return groupItems.size

        return maxSize
    }

    fun updateItems(users: List<GroupItemViewModel>){
        this.groupItems = users
        notifyDataSetChanged()
    }

    fun updateListener(listener: GroupItemListener){
        this.listener = listener
        notifyDataSetChanged()
    }

    class CreateGroupViewHolder(private val binding: MenuGroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(groupItem: GroupItemViewModel, listener: GroupItemListener) {
            binding.model = groupItem
            binding.listener = listener
        }
    }
}