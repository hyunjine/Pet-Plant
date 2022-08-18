package com.hyunjine.petplant.view.main.etc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentEtcBinding
import com.hyunjine.petplant.view.main.etc.vm.EtcViewModel

class EtcFragment: BaseFragment<FragmentEtcBinding, EtcViewModel>() {
    override fun setViewModel() {

    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentEtcBinding =
        FragmentEtcBinding.inflate(lf, ct, false)

    override fun initView() {

    }
}