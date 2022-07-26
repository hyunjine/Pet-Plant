package com.hyunjine.petplant.view.post.picture.adapter

import android.os.SystemClock
import android.util.Log
import android.widget.ImageView
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.databinding.ViewpagerPlantImageListItemBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class ViewPagerAdapter: BaseBannerAdapter<Int>() {
    private lateinit var binding: ViewpagerPlantImageListItemBinding

    override fun bindData(
        holder: BaseViewHolder<Int>,
        data: Int,
        position: Int,
        pageSize: Int
    ) {
        binding = ViewpagerPlantImageListItemBinding.bind(holder.itemView)
        binding.bannerImage.setImageResource(data)
    }

    override fun getLayoutId(viewType: Int): Int = R.layout.viewpager_plant_image_list_item
}