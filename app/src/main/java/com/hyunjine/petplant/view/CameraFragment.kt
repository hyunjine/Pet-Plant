package com.hyunjine.petplant.view

import androidx.lifecycle.ViewModelProvider
import com.hyunjine.petplant.R
import com.hyunjine.petplant.TestViewModel
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentCameraBinding

class CameraFragment: BaseFragment<FragmentCameraBinding, TestViewModel>() {
    override val layoutResourceId: Int get() = R.layout.fragment_camera

    override fun setViewModel() {
        viewModel = ViewModelProvider(this)[TestViewModel::class.java]
    }

    override fun initView() {

    }

    override fun onEvent() {

    }
}