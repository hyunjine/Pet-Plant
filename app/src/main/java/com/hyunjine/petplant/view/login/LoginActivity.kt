package com.hyunjine.petplant.view.login

import android.R
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.AuthCodeClient
import com.kakao.sdk.auth.TokenManager
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.auth.rx
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private lateinit var binding: ActivityLoginBinding
    private val functions: FirebaseFunctions by lazy { Firebase.functions }
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

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
        btnKakao.setOnClickLoginListener {
            checkLoginStatus()
        }

    }

    private fun checkLoginStatus() {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) tryLogin()
                    else Log.e(TAG, "initView: $error")
                }
                else {
                    Log.d(TAG, "initView: ${TokenManager.instance.getToken()?.accessToken}")
                }
            }
        } else {
            tryLogin()
        }
    }

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
        }
    }

    fun loginAppButtonClicked() {
        AuthCodeClient.rx.authorizeWithKakaoTalk(this)
            .observeOn(Schedulers.io())
            .flatMap { authCode -> AuthApiClient.rx.issueAccessToken(authCode) }
            .subscribe({
                getFirebaseJwt(it.accessToken, 0)
                Log.e("test", it.accessToken)
            }) { error -> /* 에러 처리 */ }.addTo(compositeDisposable)
    }

    private fun getFirebaseJwt(accessToken: String, type: Int) {
        val firebaseCustomTokenApi : Retrofit = get()

        val observable: Disposable =
            firebaseCustomTokenApi
                .create(FirebaseCustomTokenApi::class.java)
                .getFirebaseKakaoCustomToken(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ i ->     /*성공*/
                }, { error -> /*에러*/
                }).addTo(disposables)
    }

    private fun tryLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
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