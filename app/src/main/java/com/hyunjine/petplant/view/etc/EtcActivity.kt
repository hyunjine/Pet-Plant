package com.hyunjine.petplant.view.etc

import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.base.BaseActivity
import com.hyunjine.petplant.databinding.ActivityEtcBinding

class EtcActivity : BaseActivity<ActivityEtcBinding>({ ActivityEtcBinding.inflate(it)}) {
    override fun initVew() {

    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }
}