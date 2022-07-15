package com.hyunjine.petplant.view.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.ktx.Firebase
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.AuthApi
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.AuthApiManager
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private lateinit var binding: ActivityLoginBinding
    private lateinit var functions: FirebaseFunctions by lazy { Firebase.functions }

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
        val a = addMessage("하이하이 나는 양현진")
        Log.d(TAG, "initView: ${a.result}")
    }
    private fun addMessage(text: String): Task<String> {
        // Create the arguments to the callable function.
        val data = hashMapOf(
            "text" to text,
            "push" to true
        )

        return functions
            .getHttpsCallable("addMessage")
            .call(data)
            .continueWith { task ->
                // This continuation runs on either success or failure, but if the task
                // has failed then result will throw an Exception which will be
                // propagated down.
                val result = task.result?.data as String
                result
            }
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
                    AuthApiManager.instance.tokenManagerProvider
                    Log.d(TAG, "initView: ${AuthApiClient.instance.tokenManagerProvider.manager.getToken()?.accessToken}")
                    AuthApiClient.instance.tokenManagerProvider.manager.getToken()?.accessToken?.let { token ->
                        auth.signInWithCustomToken(token)
                            .addOnSuccessListener { result ->
                                val user = result.user
                                Log.d(TAG, "checkLoginStatus: ${user?.email}")
                                Log.d(TAG, "checkLoginStatus: ${user?.displayName}")
                            }.addOnFailureListener {
                                Log.e(TAG, "checkLoginStatus: ", it)
                            }
                    }

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