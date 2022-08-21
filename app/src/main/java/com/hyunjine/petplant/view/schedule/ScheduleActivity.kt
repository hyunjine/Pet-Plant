package com.hyunjine.petplant.view.schedule

import android.util.Log
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.common.base.BaseActivity
import com.hyunjine.petplant.databinding.ActivityScheduleBinding

class ScheduleActivity : BaseActivity<ActivityScheduleBinding>({ ActivityScheduleBinding.inflate(it) }){
    override fun initVew() {

    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
    }
}