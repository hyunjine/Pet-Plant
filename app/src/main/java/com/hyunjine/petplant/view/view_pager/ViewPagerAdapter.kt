package com.hyunjine.petplant.view.view_pager

import com.hyunjine.petplant.R
import com.hyunjine.petplant.databinding.ViewpagerPlantImageItemBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class ViewPagerAdapter: BaseBannerAdapter<Int>() {
    private lateinit var binding: ViewpagerPlantImageItemBinding

    override fun bindData(
        holder: BaseViewHolder<Int>,
        data: Int,
        position: Int,
        pageSize: Int
    ) {
        binding = ViewpagerPlantImageItemBinding.bind(holder.itemView)
        binding.bannerImage.setImageResource(data)
    }

    override fun getLayoutId(viewType: Int): Int = R.layout.viewpager_plant_image_item
}