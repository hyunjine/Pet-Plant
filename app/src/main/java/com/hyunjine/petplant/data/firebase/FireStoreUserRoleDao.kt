package com.hyunjine.petplant.data.firebase

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.luple.doctorsleep.common.FS_USER
import com.luple.doctorsleep.common.FS_USERS
import com.luple.doctorsleep.common.FS_USER_ROLE
import com.luple.doctorsleep.data.model.User
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxjava3.core.Single

class FireStoreUserRoleDao: UserRoleDao {
    private val userRole: CollectionReference by lazy {
        FirebaseFirestore.getInstance()
            .collection(FS_USER_ROLE)
            .document(FS_USERS)
            .collection(FS_USER)
    }

    override fun getUserData() = Single.create<User> { emitter ->
        Firebase.auth.currentUser?.apply {
            userRole.document(uid).get()
                .addOnFailureListener { emitter.onError(it) }
                .addOnSuccessListener { documentSnapshot ->
                    emitter.onSuccess(documentSnapshot.toObject(User::class.java) ?: User())
            }
        } ?: emitter.onError(userNotExistException())
    }

    override fun saveUserData(user: User) = Completable.create { emitter ->
        Firebase.auth.currentUser?.apply {
            userRole.document(uid).set(user)
                .addOnFailureListener { emitter.onError(it) }
                .addOnSuccessListener {
                    emitter.onComplete()
                }
        } ?: emitter.onError(Exception("User id is not exist"))
    }

    override fun updateUserData(field: String, data: String) =  Completable.create { emitter ->
        Firebase.auth.currentUser?.apply {
            userRole.document(uid).update(field, data)
                .addOnFailureListener { emitter.onError(it) }
                .addOnSuccessListener { emitter.onComplete() }
        } ?: emitter.onError(userNotExistException())
    }

    private fun userNotExistException(): Exception = Exception("User id is not exist")
}