package com.hyunjine.petplant.view.post.picture.adapter

import android.widget.ImageView
import com.hyunjine.petplant.R
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class ViewPagerAdapter: BaseBannerAdapter<Int>() {
    override fun bindData(
        holder: BaseViewHolder<Int>?,
        data: Int?,
        position: Int,
        pageSize: Int
    ) {
        holder?.setImageResource(R.id.banner_image, R.drawable.ic_calendar)
    }

    override fun getLayoutId(viewType: Int): Int = R.layout.viewpager_plant_image_list_item
}