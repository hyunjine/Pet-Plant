package com.hyunjine.petplant.view.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.api.LogDescriptor
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.data.model.PlantInfo
import com.hyunjine.petplant.databinding.RcMainPlantItemBinding

class MainRcAdapter : ListAdapter<PlantInfo, MainRcAdapter.HomeRvViewHolder>(MainDiff()) {
    private lateinit var listener: () -> Unit

    inner class HomeRvViewHolder(private val binding: RcMainPlantItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: PlantInfo) {
            binding.layout.setOnClickListener {
                if (::listener.isInitialized) listener()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRvViewHolder =
        HomeRvViewHolder(
            RcMainPlantItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HomeRvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MainDiff : DiffUtil.ItemCallback<PlantInfo>() {
        override fun areContentsTheSame(oldItem: PlantInfo, newItem: PlantInfo) =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: PlantInfo, newItem: PlantInfo) =
            oldItem.index == newItem.index
    }

}

