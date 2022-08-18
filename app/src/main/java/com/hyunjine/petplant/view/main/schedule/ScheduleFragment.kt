package com.hyunjine.petplant.view.main.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentScheduleBinding
import com.hyunjine.petplant.view.main.etc.vm.EtcViewModel

class ScheduleFragment: BaseFragment<FragmentScheduleBinding, EtcViewModel>() {
    override fun setViewModel() {

    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentScheduleBinding =
        FragmentScheduleBinding.inflate(lf, ct, false)

    override fun initView() {

    }
}