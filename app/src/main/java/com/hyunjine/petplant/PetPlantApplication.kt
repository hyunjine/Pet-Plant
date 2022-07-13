package com.hyunjine.petplant

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class PetPlantApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "7808202700d523f2dc8c9c2ee9b9c43b")
    }
}