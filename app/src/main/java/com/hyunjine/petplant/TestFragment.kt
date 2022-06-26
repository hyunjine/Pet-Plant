package com.hyunjine.petplant

import androidx.lifecycle.ViewModelProvider
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentTestBinding

class TestFragment: BaseFragment<FragmentTestBinding, TestViewModel>() {
    override val layoutResourceId: Int get() = R.layout.fragment_test

    override fun setViewModel() {
        viewModel = ViewModelProvider(this)[TestViewModel::class.java]
    }

    override fun initView() {

    }

    override fun onEvent() {

    }
}