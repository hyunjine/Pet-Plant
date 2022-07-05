package com.hyunjine.petplant.view.post.picture

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.internal.service.Common
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.common.util.Test
import com.hyunjine.petplant.databinding.FragmentPictureBinding
import com.hyunjine.petplant.view.post.picture.vm.PictureViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PictureFragment: BaseFragment<FragmentPictureBinding, PictureViewModel>() {

    companion object {
        val A = 0
    }
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
            binding.tv.text = A.toString()
        }
        binding.clPostPicture.setOnClickListener {
            Log.d(TAG, "post")
            startFragment(PictureFragmentDirections.actionPictureFragmentToNameFragment())
        }
    }

    private fun go() {
        arr.add(count)
        count += 1000000L

        Log.d(TAG, arr.size.toString())
    }
}