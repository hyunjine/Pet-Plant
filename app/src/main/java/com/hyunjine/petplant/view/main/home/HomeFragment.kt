package com.hyunjine.petplant.view.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentHomeBinding
import com.hyunjine.petplant.view.main.etc.vm.EtcViewModel
import com.hyunjine.petplant.view.main.home.adapter.RecentViewAdapter
import com.hyunjine.petplant.view.main.home.vm.HomeViewModel
import com.hyunjine.petplant.view.main.vm.MainViewModel

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private lateinit var activityViewModel: MainViewModel
    private lateinit var adapter: RecentViewAdapter
    override fun setViewModel() {
        activityViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(lf, ct, false)

    override fun initView() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        adapter = RecentViewAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        adapter.setDataList(arrayListOf("1", "2", "2", "2", "2", "2", "2", "2", "2", "2"))
        binding.recyclerView.setOnScrollChangeListener { _, fromX, fromY, toX, toY ->
            Log.d(TAG, "setRecyclerView: $fromX $fromY $toX $toY")
            //스크롤 아래로
            if (fromY > toY) {
                activityViewModel.scrollDown()
            }
            // 스크롤 위로
            else if (fromY < toY) {
                activityViewModel.scrollUp()
            }
        }
    }
}