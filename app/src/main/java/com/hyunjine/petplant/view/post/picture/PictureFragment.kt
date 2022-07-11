package com.hyunjine.petplant.view.post.picture

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.hyunjine.petplant.common.TAG
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

    private val arr = mutableListOf<Long>()
    private var count = 0L
    override fun initView() {
        binding.tv.setOnClickListener {
            Log.d(TAG, "tv")
            viewModel.getString()
        }
        binding.camera.setOnClickListener {
            go()
        }
        binding.clPostPicture.setOnClickListener {
            Log.d(TAG, "post")
            startFragment(PictureFragmentDirections.actionPictureFragmentToNameFragment())
        }
    }

    private fun go() {
        Log.d(TAG, "go: ")
        test = binding.tv
    }

    companion object {
        lateinit var test: TextView
    }
}