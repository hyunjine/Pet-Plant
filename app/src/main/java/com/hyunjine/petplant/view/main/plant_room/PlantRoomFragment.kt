package com.hyunjine.petplant.view.main.plant_room

import androidx.lifecycle.ViewModelProvider
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentPlantRoomBinding
import com.hyunjine.petplant.view.main.plant_room.vm.UploadPlantViewModel

class PlantRoomFragment: BaseFragment<FragmentPlantRoomBinding, UploadPlantViewModel>() {
    override val layoutResourceId: Int get() = R.layout.fragment_plant_room

    override fun setViewModel() {
        viewModel = ViewModelProvider(this)[UploadPlantViewModel::class.java]
    }

    override fun initView() {
        setView()
        onClickEvent()
    }

    private fun setView() {
        setRecyclerView()
    }

    private fun setRecyclerView() {

    }
    private fun onClickEvent() {

    }
}