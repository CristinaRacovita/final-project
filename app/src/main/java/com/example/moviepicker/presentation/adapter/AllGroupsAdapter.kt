package com.example.moviepicker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepicker.R
import com.example.moviepicker.databinding.ItemAllGroupsBinding
import com.example.moviepicker.domain.items.AllGroupsItem
import com.example.moviepicker.presentation.listener.GroupListener

class AllGroupsAdapter : RecyclerView.Adapter<AllGroupsAdapter.AllGroupsViewHolder>() {
    private lateinit var groups: List<AllGroupsItem>
    private lateinit var listener: GroupListener

    class AllGroupsViewHolder(private val binding: ItemAllGroupsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AllGroupsItem, listener: GroupListener) {
            binding.model = item
            binding.listener = listener
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllGroupsViewHolder {
        val binding: ItemAllGroupsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_all_groups,
            parent,
            false
        )

        return AllGroupsViewHolder(binding)
    }

    fun updateGroups(groups: List<AllGroupsItem>) {
        this.groups = groups
        notifyDataSetChanged()
    }

    fun updateListener(listener: GroupListener) {
        this.listener = listener
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AllGroupsViewHolder, position: Int) {
        val item = groups[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int {
        return groups.size
    }
}