package com.hyunjine.petplant.common.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel: ViewModel() {
//    protected val _viewEvent: MutableLiveData<St> by lazy { MutableLiveData() }
//    val viewEvent: LiveData<U>
//        get() = _viewEvent

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}