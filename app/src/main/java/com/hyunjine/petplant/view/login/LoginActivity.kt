package com.hyunjine.petplant.view.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        initView()
    }

    private fun setBinding() = ActivityLoginBinding.inflate(layoutInflater).also {
        binding = it
        setContentView(it.root)
    }

    private fun initView() {
        onViewEvent()
    }

    private fun onViewEvent() = binding.run {
        btnKakao.setOnClickLoginListener { checkLoginStatus() }
    }

    private fun checkLoginStatus() {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) tryLogin()
                    else Log.e(TAG, "initView: $error")
                }
                else {
                    Log.d(TAG, "initView: ${AuthApiClient.instance.tokenManagerProvider.manager.getToken()?.accessToken}")
                }
            }
        }
        else {
            tryLogin()
        }
    }

    private fun tryLogin() = UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
        if (error != null) {
            Log.e(TAG, "로그인 실패", error)
        }
        else if (token != null) {
            Log.i(TAG, "로그인 성공 ${token.accessToken}")
        }
    }

    private fun tryLogout() = UserApiClient.instance.logout { error ->
        if (error != null) {
            Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
        }
        else {
            Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
        }
    }
}