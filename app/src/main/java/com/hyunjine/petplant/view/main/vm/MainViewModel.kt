package com.hyunjine.petplant.view.main.vm

import com.hyunjine.petplant.common.SCROLL_DOWN
import com.hyunjine.petplant.common.SCROLL_UP
import com.hyunjine.petplant.common.base.BaseViewModel

class MainViewModel: BaseViewModel() {
    private lateinit var scrollEventListener: (Int) -> Unit
    private var isScrollUp = false
    private var isScrollDown = false

    private lateinit var moveScrollListener: () -> Unit

    fun setOnChangeScrollListener(scrollEventListener: (Int) -> Unit) {
        this.scrollEventListener = scrollEventListener
    }
    fun scrollUpEvent() {
        if (::scrollEventListener.isInitialized && !isScrollUp) {
            isScrollUp = true
            isScrollDown = false
            scrollEventListener(SCROLL_UP)
        }
    }

    fun scrollDownEvent() {
        if (::scrollEventListener.isInitialized && !isScrollDown) {
            isScrollDown = true
            isScrollUp = false
            scrollEventListener(SCROLL_DOWN)
        }
    }

    fun setOnMoveScrollTopListener(moveScrollListener: () -> Unit) {
        this.moveScrollListener = moveScrollListener
    }
    fun moveScrollTop() {
        if (::moveScrollListener.isInitialized) moveScrollListener()
    }
}