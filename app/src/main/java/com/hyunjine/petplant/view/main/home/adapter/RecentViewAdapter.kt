package com.hyunjine.petplant.view.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyunjine.petplant.databinding.RcMainPlantItemBinding

class RecentViewAdapter : RecyclerView.Adapter<RecentViewAdapter.RecentViewHolder>() {
    private lateinit var listener: () -> Unit

    private var list = ArrayList<String>()

    inner class RecentViewHolder(val binding: RcMainPlantItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: String) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder =
        RecentViewHolder(
            RcMainPlantItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        holder.bind(list[position])
        // 검색어 클릭
        holder.itemView.setOnClickListener {
            if (::listener.isInitialized) listener()
        }
    }

    fun setDataList(list: ArrayList<String>) {
        this.list = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = list.size
}

