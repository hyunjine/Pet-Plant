package com.hyunjine.petplant.view.main

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.hyunjine.petplant.common.base.BaseActivity
import com.hyunjine.petplant.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    override fun initVew() {
        setView()
    }

    private fun setView() = binding.run {
        val params = bottomAppBar.layoutParams as CoordinatorLayout.LayoutParams
        params.behavior = object : HideListenableBottomAppBarBehavior() {
            override fun onSlideDown() {
                imgCalendar.visibility = View.GONE
                imgEtc.visibility = View.GONE
                tvCalendar.visibility = View.GONE
                tvEtc.visibility = View.GONE
            }

            override fun onSlideUp() {
                imgCalendar.visibility = View.VISIBLE
                imgEtc.visibility = View.VISIBLE
                tvCalendar.visibility = View.VISIBLE
                tvEtc.visibility = View.VISIBLE
            }
        }
    }
}