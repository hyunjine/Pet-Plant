package com.hyunjine.petplant.view.post.picture

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.lifecycle.ViewModelProvider
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentPictureBinding
import com.hyunjine.petplant.view.post.picture.adapter.ViewPagerAdapter
import com.hyunjine.petplant.view.post.picture.vm.PictureViewModel
import com.zhpan.bannerview.BannerViewPager

class PictureFragment: BaseFragment<FragmentPictureBinding, PictureViewModel>() {
    private lateinit var mViewPager: BannerViewPager<Int>

    private fun setupViewPager() = binding.run {

        (bannerView as BannerViewPager<Int>).also { mViewPager = it }
        bannerView.apply {
            adapter = ViewPagerAdapter()
            setLifecycleRegistry(lifecycle)
        }.create()
    }

    override fun setViewModel() {
        viewModel = ViewModelProvider(this)[PictureViewModel::class.java]
        viewModel.activity = this
    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentPictureBinding =
        FragmentPictureBinding.inflate(lf, ct, false)

    override fun initView() {
        setupViewPager()
        binding.tv.setOnClickListener {
            mViewPager.refreshData(mutableListOf(R.drawable.ic_add, R.drawable.ic_add, R.drawable.ic_add))
        }
        binding.imgCamera.setOnClickListener {
        }
        binding.clPostPicture.setOnClickListener {
            startFragment(PictureFragmentDirections.actionPictureFragmentToNameFragment())
        }
    }
}