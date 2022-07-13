package com.hyunjine.petplant.data.firebase

class FireStoreUserRoleDao {
//    private val userRole: CollectionReference by lazy {
//        FirebaseFirestore.getInstance()
//            .collection(FS_USER_ROLE)
//            .document(FS_USERS)
//            .collection(FS_USER)
//    }

//    override fun getUserData() = Single.create<User> { emitter ->
//        Firebase.auth.currentUser?.apply {
//            userRole.document(uid).get()
//                .addOnFailureListener { emitter.onError(it) }
//                .addOnSuccessListener { documentSnapshot ->
//                    emitter.onSuccess(documentSnapshot.toObject(User::class.java) ?: User())
//            }
//        } ?: emitter.onError(userNotExistException())
//    }
//
//    override fun saveUserData(user: User) = Completable.create { emitter ->
//        Firebase.auth.currentUser?.apply {
//            userRole.document(uid).set(user)
//                .addOnFailureListener { emitter.onError(it) }
//                .addOnSuccessListener {
//                    emitter.onComplete()
//                }
//        } ?: emitter.onError(Exception("User id is not exist"))
//    }
//
//    override fun updateUserData(field: String, data: String) =  Completable.create { emitter ->
//        Firebase.auth.currentUser?.apply {
//            userRole.document(uid).update(field, data)
//                .addOnFailureListener { emitter.onError(it) }
//                .addOnSuccessListener { emitter.onComplete() }
//        } ?: emitter.onError(userNotExistException())
//    }

    private fun userNotExistException(): Exception = Exception("User id is not exist")
}