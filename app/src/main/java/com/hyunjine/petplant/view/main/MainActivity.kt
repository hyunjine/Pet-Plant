package com.hyunjine.petplant.view.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.FIRST_POSITION
import com.hyunjine.petplant.common.base.BaseActivity
import com.hyunjine.petplant.common.enum.AnimationDirection
import com.hyunjine.petplant.common.util.RecyclerViewScrollAnimation
import com.hyunjine.petplant.data.model.PlantInfo
import com.hyunjine.petplant.databinding.ActivityMainBinding
import com.hyunjine.petplant.view.etc.EtcActivity
import com.hyunjine.petplant.view.main.adapter.MainRcAdapter
import com.hyunjine.petplant.view.main.vm.MainViewModel
import com.hyunjine.petplant.view.schedule.ScheduleActivity

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    private lateinit var viewModel: MainViewModel
    private lateinit var rcAnimUtil: RecyclerViewScrollAnimation
    private val rcAdapter: MainRcAdapter by lazy { MainRcAdapter() }

    override fun initVew() {
        setViewModel()
        setRecyclerView()
        onEvent()
    }

    private fun onEvent() {
        onClickEvent()
    }

    private fun setRecyclerView() = binding.recyclerView.apply {
        adapter = rcAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
        setHasFixedSize(true)
        rcAnimUtil = RecyclerViewScrollAnimation(binding.appBar, binding.btnHome)
        val list = arrayListOf<PlantInfo>()
        for (i: Int in 1..10) {
            list.add(PlantInfo(i))
        }
        rcAdapter.submitList(list)
        setOnScrollChangeListener { _, _, fromY, _, toY ->
            val isScrollUp = fromY > toY
            val isScrollDown = fromY < toY

            if (isScrollUp)
                rcAnimUtil.hideView()
            else if (isScrollDown)
                rcAnimUtil.showView()
        }
    }

    private fun onClickEvent() = binding.run {
        btnHome.setOnClickListener {
            recyclerView.smoothScrollToPosition(FIRST_POSITION)
        }
        laySchedule.setOnClickListener {
            startActivity(ScheduleActivity::class.java, AnimationDirection.SLIDE_RIGHT)
        }
        layEtc.setOnClickListener {
            startActivity(EtcActivity::class.java, AnimationDirection.SLIDE_LEFT)
        }
    }

    private fun startActivity(classType: Class<*>, animDirection: AnimationDirection) {
        startActivity(Intent(this, classType))
        if (animDirection == AnimationDirection.SLIDE_LEFT) {
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        } else if(animDirection == AnimationDirection.SLIDE_RIGHT) {
            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
        }
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }
}