package com.hyunjine.petplant.view.main.plant_room

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentPlantRoomBinding
import com.hyunjine.petplant.view.main.plant_room.adapter.RecyclerPlantListAdapter
import com.hyunjine.petplant.view.main.plant_room.vm.PlantRoomViewModel

class PlantRoomFragment: BaseFragment<FragmentPlantRoomBinding, PlantRoomViewModel>() {
    override val layoutResourceId: Int get() = R.layout.fragment_plant_room

    override fun setViewModel() {
        viewModel = ViewModelProvider(this)[PlantRoomViewModel::class.java]
    }

    override fun initView() {
        setView()
        onClickEvent()
    }

    private fun setView() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val listManager = GridLayoutManager(requireContext(), 3)
        val listAdapter = RecyclerPlantListAdapter().apply {
            setDataList(arrayListOf("Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"))
        }
        binding.run {
            rcPlantList.apply {
                setHasFixedSize(true)
                layoutManager = listManager
                adapter = listAdapter
            }
        }
    }

    private fun onClickEvent() {

    }
}