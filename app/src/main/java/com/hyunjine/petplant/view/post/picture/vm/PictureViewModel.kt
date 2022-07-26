package com.hyunjine.petplant.view.post.picture.vm

import com.hyunjine.petplant.common.base.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

class PictureViewModel: BaseViewModel() {
    private val compositeDisposables: CompositeDisposable by lazy { CompositeDisposable() }


    fun detachView() {
        compositeDisposables.dispose()
    }
}