package com.hyunjine.petplant.view.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentHomeBinding
import com.hyunjine.petplant.view.main.etc.vm.EtcViewModel

class HomeFragment: BaseFragment<FragmentHomeBinding, EtcViewModel>() {
    override fun setViewModel() {

    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(lf, ct, false)

    override fun initView() {

    }
}