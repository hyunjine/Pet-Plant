package com.hyunjine.petplant.common.util

import android.view.View
import android.view.animation.TranslateAnimation
import com.hyunjine.petplant.common.dp

class RecyclerViewScrollAnimation(private val fullAnimView: View, private val halfAnimView: View) {
    private var isHide = false
    private var isShow = false

    fun showView() {
        if (!isShow) {
            isShow = true
            isHide = false
            showFullAnim(fullAnimView)
            showHalfAnim(halfAnimView)
        }
    }

    fun hideView() {
        if (!isHide) {
            isHide = true
            isShow = false
            hideFullAnim(fullAnimView)
            halfHalfAnim(halfAnimView)
        }
    }

    private fun showFullAnim(view: View) {
        view.visibility = View.VISIBLE
        TranslateAnimation(0f, 0f, view.height.toFloat(), 0f).apply {
            duration = 250
            view.startAnimation(this)
        }
    }

    private fun hideFullAnim(view: View) {
        view.visibility = View.INVISIBLE
        TranslateAnimation(0f, 0f, 0f, view.height.toFloat()).apply {
            duration = 250
            view.startAnimation(this)
        }
    }

    private fun showHalfAnim(view: View) = TranslateAnimation(0f, 0f, 20f.dp, 0f).apply {
        duration = 350
        fillAfter = true
        view.startAnimation(this)
    }

    private fun halfHalfAnim(view: View) = TranslateAnimation(0f, 0f, 0f, 20f.dp).apply {
        duration = 250
        fillAfter = true
        view.startAnimation(this)
    }
}