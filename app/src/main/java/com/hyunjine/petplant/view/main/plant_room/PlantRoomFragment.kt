package com.hyunjine.petplant.view.main.plant_room

import androidx.lifecycle.ViewModelProvider
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentPlantRoomBinding
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

    }

    private fun onClickEvent() {

    }
}