package com.hyunjine.petplant.view.main

import android.view.View
import android.view.animation.TranslateAnimation
import androidx.lifecycle.ViewModelProvider
import com.hyunjine.petplant.common.SCROLL_DOWN
import com.hyunjine.petplant.common.SCROLL_UP
import com.hyunjine.petplant.common.base.BaseActivity
import com.hyunjine.petplant.common.dp
import com.hyunjine.petplant.databinding.ActivityMainBinding
import com.hyunjine.petplant.view.main.vm.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    private lateinit var viewModel: MainViewModel

    override fun initVew() {
        setViewModel()
        setView()
        onEvent()
    }

    private fun setView() = binding.run {

    }

    private fun onEvent() {
        onClickEvent()
        onViewModelEvent()
    }

    private fun onClickEvent() = binding.run {
        btnHome.setOnClickListener {
            viewModel.moveScrollTop()
        }
    }

    private fun onViewModelEvent() = binding.run {
        viewModel.setOnChangeScrollListener { scroll ->
            if (scroll == SCROLL_UP) {
                slideUp(bottomNavigation)
                slideUp2(btnHome)
            } else if (scroll == SCROLL_DOWN) {
                slideDown(bottomNavigation)
                slideDown2(btnHome)
            }
        }
    }

    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            0f
        ) // toYDelta
        animate.duration = 250

        view.startAnimation(animate)
    }

    // slide the view from its current position to below itself
    fun slideDown(view: View) {
        view.visibility = View.INVISIBLE
        val animate = TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            0f,  // fromYDelta
            view.height.toFloat()
        ) // toYDelta
        animate.duration = 250

        view.startAnimation(animate)
    }

    fun slideUp2(view: View) = TranslateAnimation(
        0f,
        0f,
        20f.dp,
        0f
    ).apply {
        duration = 350
        fillAfter = true
        view.startAnimation(this)
    }


    // slide the view from its current position to below itself
    fun slideDown2(view: View) = TranslateAnimation(
        0f,
        0f,
        0f,
        20f.dp
    ).apply {
        duration = 250
        fillAfter = true
        view.startAnimation(this)
    }
    private fun setViewModel() {
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }
}