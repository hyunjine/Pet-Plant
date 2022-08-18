package com.hyunjine.petplant.view.main.vm

import com.hyunjine.petplant.common.SCROLL_DOWN
import com.hyunjine.petplant.common.SCROLL_UP
import com.hyunjine.petplant.common.base.BaseViewModel

class MainViewModel: BaseViewModel() {
    private lateinit var scrollListener: (Int) -> Unit
    private var isScrollUp = false
    private var isScrollDown = false
    fun setOnChangeScrollListener(scrollListener: (Int) -> Unit) {
        this.scrollListener = scrollListener
    }
    fun scrollUp() {
        if (::scrollListener.isInitialized && !isScrollUp) {
            isScrollUp = true
            isScrollDown = false
            scrollListener(SCROLL_UP)
        }
    }

    fun scrollDown() {
        if (::scrollListener.isInitialized && !isScrollDown) {
            isScrollDown = true
            isScrollUp = false
            scrollListener(SCROLL_DOWN)
        }
    }
}