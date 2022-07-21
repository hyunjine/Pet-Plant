package com.hyunjine.petplant.view.login.vm

import android.util.Log
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.common.base.BaseViewModel
import com.hyunjine.petplant.data.retrofit.FirebaseTokenClient
import com.kakao.sdk.auth.AuthCodeClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class LoginViewModel : BaseViewModel() {
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun getToken() {
        Log.d(TAG, "getToken: ")
        FirebaseTokenClient.getClient().getFirebaseToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "initView: $it")
            }, {
                Log.e(TAG, "initView: ", it)
            }).addTo(compositeDisposable)
    }
}