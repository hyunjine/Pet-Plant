package com.hyunjine.petplant.view.post.name

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentNameBinding
import com.hyunjine.petplant.view.post.name.vm.NameViewModel

class NameFragment: BaseFragment<FragmentNameBinding, NameViewModel>() {
    override fun setViewModel() {

    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentNameBinding =
        FragmentNameBinding.inflate(lf, ct, false)

    override fun initView() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}