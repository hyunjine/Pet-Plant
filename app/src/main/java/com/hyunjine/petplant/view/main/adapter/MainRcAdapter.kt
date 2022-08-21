package com.hyunjine.petplant.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hyunjine.petplant.data.model.PlantInfo
import com.hyunjine.petplant.databinding.RcMainPlantItemBinding

class MainRcAdapter : ListAdapter<PlantInfo, MainRcAdapter.HomeRvViewHolder>(diffUtil) {
    private lateinit var listener: () -> Unit

    inner class HomeRvViewHolder(val binding: RcMainPlantItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: PlantInfo) {

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
        // 검색어 클릭
        holder.itemView.setOnClickListener {
            if (::listener.isInitialized) listener()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PlantInfo>() {
            override fun areContentsTheSame(oldItem: PlantInfo, newItem: PlantInfo) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: PlantInfo, newItem: PlantInfo) =
                oldItem.index == newItem.index
        }
    }
}

