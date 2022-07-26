package com.hyunjine.petplant.common.util.media_select.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyunjine.petplant.databinding.MultiImageItemBinding


class MediaSelectAdapter: RecyclerView.Adapter<MediaSelectAdapter.MediaSelectHolder>() {
    private var mediaList = mutableListOf<Uri>()

    fun setMediaList(list: MutableList<Uri>) {
        mediaList = list
        notifyDataSetChanged()
    }
    inner class MediaSelectHolder(val binding: MultiImageItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaSelectHolder =
        MediaSelectHolder(
            MultiImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MediaSelectHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mediaList[position])
            .into(holder.binding.image)
    }

    override fun getItemCount(): Int = mediaList.size
}