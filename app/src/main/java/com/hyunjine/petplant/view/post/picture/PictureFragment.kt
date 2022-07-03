package com.hyunjine.petplant.view.post.picture

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentPictureBinding
import com.hyunjine.petplant.view.post.picture.vm.PictureViewModel

class PictureFragment: BaseFragment<FragmentPictureBinding, PictureViewModel>() {

    override fun setViewModel() {

    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentPictureBinding =
        FragmentPictureBinding.inflate(lf, ct, false)

    override fun initView() {

    }
}