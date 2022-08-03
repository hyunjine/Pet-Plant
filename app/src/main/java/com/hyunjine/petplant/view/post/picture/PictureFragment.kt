package com.hyunjine.petplant.view.post.picture

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.common.util.media_select.MediaSelectActivity
import com.hyunjine.petplant.databinding.FragmentPictureBinding
import com.hyunjine.petplant.view.main.MainActivity
import com.hyunjine.petplant.view.post.picture.vm.PictureViewModel
import com.hyunjine.petplant.view.view_pager.ViewPagerFragment

class PictureFragment: BaseFragment<FragmentPictureBinding, PictureViewModel>() {
    override fun initView() = binding.run {
        setViewPager()
        onClickEvent()
    }

    private fun setViewPager() = ViewPagerFragment().run {
        setViewPagerFragment(this)
        setOnViewCreatedListener {
            val imageList = mutableListOf(
                R.drawable.bg_round_edge_rectangle_gray_border,
                R.drawable.bg_round_edge_rectangle_gray_border,
                R.drawable.bg_round_edge_rectangle_gray_border
            )
            setImageResource(imageList)
        }
    }

    private fun setViewPagerFragment(fragment: ViewPagerFragment) =
        requireActivity().supportFragmentManager.beginTransaction().run {
            replace(binding.fragViewPager.id, fragment)
            commit()
            requireContext().fi
        }

    private fun onClickEvent() = binding.run {
        imgCamera.setOnClickListener {
        }
        clPostPicture.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), MediaSelectActivity::class.java))
        }
    }

    override fun setViewModel() {
        viewModel = ViewModelProvider(this)[PictureViewModel::class.java]
    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentPictureBinding =
        FragmentPictureBinding.inflate(lf, ct, false)
}
