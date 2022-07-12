package com.hyunjine.petplant.view.post.picture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentPictureBinding
import com.hyunjine.petplant.view.post.picture.vm.PictureViewModel

class PictureFragment: BaseFragment<FragmentPictureBinding, PictureViewModel>() {

    override fun setViewModel() {
        viewModel = ViewModelProvider(this)[PictureViewModel::class.java]
        viewModel.activity = this
    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentPictureBinding =
        FragmentPictureBinding.inflate(lf, ct, false)

    override fun initView() {
        binding.tv.setOnClickListener {
        }
        binding.camera.setOnClickListener {
        }
        binding.clPostPicture.setOnClickListener {
            startFragment(PictureFragmentDirections.actionPictureFragmentToNameFragment())
        }
    }
}