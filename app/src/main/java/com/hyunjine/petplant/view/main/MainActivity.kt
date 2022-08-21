package com.hyunjine.petplant.view.main

import android.content.Intent
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.base.BaseActivity
import com.hyunjine.petplant.common.dp
import com.hyunjine.petplant.data.model.PlantInfo
import com.hyunjine.petplant.databinding.ActivityMainBinding
import com.hyunjine.petplant.view.main.adapter.MainRcAdapter
import com.hyunjine.petplant.view.main.vm.MainViewModel
import com.hyunjine.petplant.view.post.PostActivity

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    private lateinit var viewModel: MainViewModel
    private val rcAdapter: MainRcAdapter by lazy { MainRcAdapter() }
    private var isScrollUp = false
    private var isScrollDown = false

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

        val list = arrayListOf<PlantInfo>()
        for (i: Int in 1..10) {
            list.add(PlantInfo(i))
        }
        rcAdapter.submitList(list)
        recyclerView.setOnScrollChangeListener { _, _, fromY, _, toY ->
            // Scroll Up Event
            if (fromY > toY && !isScrollUp) {
                isScrollUp = true
                isScrollDown = false
                appBarSlideUp(appBar)
                fabSlideUp(btnHome)
            }
            // Scroll Down Event
            else if (fromY < toY && !isScrollDown) {
                isScrollDown = true
                isScrollUp = false
                appBarSown(appBar)
                fabSlideDown(btnHome)
            }
        }
    }

    private fun onClickEvent() = binding.run {
        btnHome.setOnClickListener {
            recyclerView.smoothScrollToPosition(0)
        }
        laySchedule.setOnClickListener {
            startActivity(Intent(this@MainActivity, PostActivity::class.java))
            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
        }
        layEtc.setOnClickListener {
            startActivity(Intent(this@MainActivity, PostActivity::class.java))
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        }
    }

    private fun appBarSlideUp(view: View) {
        view.visibility = View.VISIBLE
        TranslateAnimation(0f, 0f, view.height.toFloat(), 0f).apply {
            duration = 250
            view.startAnimation(this)
        }
    }

    // slide the view from its current position to below itself
    private fun appBarSown(view: View) {
        view.visibility = View.INVISIBLE
        TranslateAnimation(0f, 0f, 0f, view.height.toFloat()).apply {
            duration = 250
            view.startAnimation(this)
        }
    }

    private fun fabSlideUp(view: View) = TranslateAnimation(0f, 0f, 20f.dp, 0f).apply {
        duration = 350
        fillAfter = true
        view.startAnimation(this)
    }


    // slide the view from its current position to below itself
    private fun fabSlideDown(view: View) = TranslateAnimation(0f, 0f, 0f, 20f.dp).apply {
        duration = 250
        fillAfter = true
        view.startAnimation(this)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }
}