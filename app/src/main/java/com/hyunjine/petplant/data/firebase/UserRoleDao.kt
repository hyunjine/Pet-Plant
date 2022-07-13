package com.hyunjine.petplant.data.firebase

import com.hyunjine.petplant.data.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserRoleDao {
    fun getUserData(): Single<User>
    fun saveUserData(user: User): Completable
    fun updateUserData(field: String, data: String): Completable
}