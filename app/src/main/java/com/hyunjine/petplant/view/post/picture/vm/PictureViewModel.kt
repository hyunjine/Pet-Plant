package com.hyunjine.petplant.view.post.picture.vm

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.common.base.BaseViewModel
import com.hyunjine.petplant.common.util.Test
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

class PictureViewModel: BaseViewModel() {
    var activity: Fragment? = null

    private val compositeDisposables: CompositeDisposable by lazy { CompositeDisposable() }

    fun addDisposables(disposable: Disposable) {
        compositeDisposables.add(disposable)
    }


    private var value = "a"
    val observer: PublishSubject<String> = PublishSubject.create()

    fun getString() {
        addDisposables(Test().start()
            .subscribe {
                Log.d(TAG, it)
            })
    }

    fun detachView() {
        compositeDisposables.dispose()
    }
}