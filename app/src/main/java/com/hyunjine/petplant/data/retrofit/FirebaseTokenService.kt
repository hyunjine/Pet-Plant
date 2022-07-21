package com.hyunjine.petplant.data.retrofit

import io.reactivex.Single
import retrofit2.http.POST

interface FirebaseTokenService {
    // 1. 기본 정보 목록 조회
    // numOfRows / pageNo
    @POST("verifyToken")
    fun getFirebaseToken(): Single<String>

}