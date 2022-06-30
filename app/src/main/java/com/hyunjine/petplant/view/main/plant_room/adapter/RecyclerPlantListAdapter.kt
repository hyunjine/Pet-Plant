package com.hyunjine.petplant.view.main.plant_room.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.databinding.RecyclerPlantListItemBinding

class RecyclerPlantListAdapter: RecyclerView.Adapter<RecyclerPlantListAdapter.PlantListHolder>() {
    private lateinit var dataList: ArrayList<String>

    inner class PlantListHolder(private val binding: RecyclerPlantListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String){
            binding.item.text = data
            Log.d(TAG, "bind: $data")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantListHolder =
        PlantListHolder(
            RecyclerPlantListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PlantListHolder, position: Int) {
        holder.bind(dataList[position])
        // 검색어 클릭
    }

    override fun getItemCount(): Int = dataList.size

    fun setDataList(dataList: ArrayList<String>) = dataList.let {
        this.dataList = dataList
    }
}

