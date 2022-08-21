package com.hyunjine.petplant.common

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()

val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f)

fun RecyclerView.setScrollVisibility(vararg view: View) {
    setOnScrollChangeListener { _, _, fromY, _, toY ->

    }
}