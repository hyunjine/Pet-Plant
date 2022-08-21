package com.hyunjine.petplant.view.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.data.model.PlantInfo
import com.hyunjine.petplant.databinding.FragmentHomeBinding
import com.hyunjine.petplant.view.main.home.adapter.HomeRvAdapter
import com.hyunjine.petplant.view.main.home.vm.HomeViewModel
import com.hyunjine.petplant.view.main.vm.MainViewModel

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private lateinit var activityViewModel: MainViewModel
    private val rvAdapter: HomeRvAdapter by lazy { HomeRvAdapter() }
    override fun setViewModel() {
        activityViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(lf, ct, false)

    override fun initView() {
        setRecyclerView()
        onClickEvent()
    }

    private fun setRecyclerView() = binding.recyclerView.apply {
        adapter = rvAdapter
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)

        val list = arrayListOf<PlantInfo>()
        for (i: Int in 1..10) {
            list.add(PlantInfo(i))
        }
        rvAdapter.submitList(list)
        setOnScrollChangeListener { _, _, fromY, _, toY ->
            // Scroll Up Event
            if (fromY > toY) {
                activityViewModel.scrollDownEvent()
            }
            // Scroll Down Event
            else if (fromY < toY) {
                activityViewModel.scrollUpEvent()
            }
        }
    }

    private fun onClickEvent() = binding.run {
        activityViewModel.setOnMoveScrollTopListener {
            recyclerView.smoothScrollToPosition(0)
        }
    }
}