package com.hyunjine.petplant.view.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.data.retrofit.FirebaseTokenClient
import com.hyunjine.petplant.databinding.ActivityLoginBinding
import com.hyunjine.petplant.view.login.vm.LoginViewModel
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.TokenManager
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers


class LoginActivity : AppCompatActivity() {
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private lateinit var binding: ActivityLoginBinding
    private val functions: FirebaseFunctions by lazy { Firebase.functions }
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        initView()
    }

    private fun setBinding() = ActivityLoginBinding.inflate(layoutInflater).also {
        binding = it
        setContentView(it.root)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    private fun initView() {
        onViewEvent()
        viewModel.getToken()
    }

    private fun onViewEvent() = binding.run {
//        btnKakao.setOnClickLoginListener {
//            checkLoginStatus()
//        }

    }

//    private fun checkLoginStatus() {
//        if (AuthApiClient.instance.hasToken()) {
//            UserApiClient.instance.accessTokenInfo { _, error ->
//                if (error != null) {
//                    if (error is KakaoSdkError && error.isInvalidTokenError()) tryLogin()
//                    else Log.e(TAG, "initView: $error")
//                }
//                else {
//
//                }
//            }
//        } else {
//            tryLogin()
//        }
//    }
//
//    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
//        if (error != null) {
//            Log.e(TAG, "카카오계정으로 로그인 실패", error)
//        } else if (token != null) {
//            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
//        }
//    }
//
//
//
//
//    private fun tryLogin() {
//
//    }
//
//    private fun tryLogout() = UserApiClient.instance.logout { error ->
//        if (error != null) {
//            Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
//        }
//        else {
//            Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
//        }
//    }
}