package com.hyunjine.petplant.view.view_pager

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyunjine.petplant.R
import com.hyunjine.petplant.databinding.FragmentViewPagerBinding
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

class ViewPagerFragment: Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var viewPager: BannerViewPager<Uri>
    private lateinit var viewCreatedListener: () -> Unit
    fun setOnViewCreatedListener(listener: () -> Unit) {
        viewCreatedListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = setBinding(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewAdapter()
        readyToInitView()
    }

    private fun setViewAdapter() = binding.run {
        viewPager = binding.root.findViewById(R.id.banner_view)

        viewPager.apply {
            adapter = ViewPagerAdapter()
            setLifecycleRegistry(lifecycle)
            setCanLoop(false)
            setAutoPlay(false)
            setPageStyle(PageStyle.MULTI_PAGE_OVERLAP)
            setRevealWidth(100)
            setPageMargin(20)
            setIndicatorStyle(IndicatorStyle.CIRCLE)
            setIndicatorSlideMode(IndicatorSlideMode.WORM)
            setIndicatorSliderColor(
                requireContext().getColor(R.color.purple_500),
                requireContext().getColor(R.color.black)
            )
        }.create()
    }

    private fun readyToInitView() {
        if (::viewCreatedListener.isInitialized) {
            viewCreatedListener()
        }
    }

    fun setImageResource(list: MutableList<Uri>) {
        viewPager.refreshData(list)
    }

    private fun setBinding(inflater: LayoutInflater, container: ViewGroup?, ): View =
        FragmentViewPagerBinding.inflate(inflater, container, false).let {
            binding = it
            it.root
        }
}