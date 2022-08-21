package com.hyunjine.petplant.view.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.base.BaseActivity
import com.hyunjine.petplant.common.util.RecyclerViewScrollAnimation
import com.hyunjine.petplant.data.model.PlantInfo
import com.hyunjine.petplant.databinding.ActivityMainBinding
import com.hyunjine.petplant.view.etc.EtcActivity
import com.hyunjine.petplant.view.main.adapter.MainRcAdapter
import com.hyunjine.petplant.view.main.vm.MainViewModel
import com.hyunjine.petplant.view.schedule.ScheduleActivity

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    private lateinit var viewModel: MainViewModel
    private val rcAdapter: MainRcAdapter by lazy { MainRcAdapter() }
    private lateinit var rcAnimUtil: RecyclerViewScrollAnimation

    override fun initVew() {
        setViewModel()
        setRecyclerView()
        setView()
        onEvent()
    }

    private fun setView() = binding.run {
    }

    private fun onEvent() {
        onClickEvent()
    }

    private fun setRecyclerView() = binding.apply {
        recyclerView.adapter = rcAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.setHasFixedSize(true)
        rcAnimUtil = RecyclerViewScrollAnimation(appBar, btnHome)

        val list = arrayListOf<PlantInfo>()
        for (i: Int in 1..10) {
            list.add(PlantInfo(i))
        }
        rcAdapter.submitList(list)
        recyclerView.setOnScrollChangeListener { _, _, fromY, _, toY ->
            // Scroll Up Event
            if (fromY > toY)
                rcAnimUtil.hideView()
            // Scroll Down Event
            else if (fromY < toY)
                rcAnimUtil.showView()
        }
    }

    private fun onClickEvent() = binding.run {
        btnHome.setOnClickListener {
            recyclerView.smoothScrollToPosition(0)
        }
        laySchedule.setOnClickListener {
            startActivity(Intent(this@MainActivity, ScheduleActivity::class.java))
            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
        }
        layEtc.setOnClickListener {
            startActivity(Intent(this@MainActivity, EtcActivity::class.java))
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        }
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }
}