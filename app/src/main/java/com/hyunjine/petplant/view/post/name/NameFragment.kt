package com.hyunjine.petplant.view.post.name

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentNameBinding
import com.hyunjine.petplant.view.post.name.vm.NameViewModel
import com.hyunjine.petplant.view.post.picture.PictureFragment

class NameFragment: BaseFragment<FragmentNameBinding, NameViewModel>() {
    override fun setViewModel() {

    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentNameBinding =
        FragmentNameBinding.inflate(lf, ct, false)

    override fun initView() {

    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: ")
        super.onDestroyView()
    }
}