package com.hyunjine.petplant

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class PetPlantApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_APP_API_KEY)
        startKoin {
            androidContext(this@PetPlantApplication)
            modules(
                listOf(getNetworkModule("개인 서버 주소."))
            )
        }
    }
}