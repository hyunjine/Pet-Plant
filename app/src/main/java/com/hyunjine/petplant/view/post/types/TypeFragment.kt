package com.hyunjine.petplant.view.post.types

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentTypeBinding
import com.hyunjine.petplant.view.post.types.vm.TypeViewModel

class TypeFragment: BaseFragment<FragmentTypeBinding, TypeViewModel>() {

    override fun setViewModel() {

    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentTypeBinding =
        FragmentTypeBinding.inflate(lf, ct, false)

    override fun initView() {

    }
}