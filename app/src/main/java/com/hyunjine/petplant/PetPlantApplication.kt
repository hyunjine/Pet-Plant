package com.hyunjine.petplant

import android.app.Application
import android.util.Log
import com.hyunjine.petplant.common.TAG
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility

class PetPlantApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "7808202700d523f2dc8c9c2ee9b9c43b")
    }
}