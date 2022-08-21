package com.hyunjine.petplant.view.view_pager

import android.net.Uri
import com.bumptech.glide.Glide
import com.hyunjine.petplant.R
import com.hyunjine.petplant.databinding.VpPlantImageItemBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class ViewPagerAdapter: BaseBannerAdapter<Uri>() {
    private lateinit var binding: VpPlantImageItemBinding

    override fun bindData(
        holder: BaseViewHolder<Uri>,
        uri: Uri,
        position: Int,
        pageSize: Int
    ) {
        binding = VpPlantImageItemBinding.bind(holder.itemView)
        Glide.with(holder.itemView)
            .load(uri)
            .into(binding.bannerImage)
    }

    override fun getLayoutId(viewType: Int): Int = R.layout.vp_plant_image_item
}