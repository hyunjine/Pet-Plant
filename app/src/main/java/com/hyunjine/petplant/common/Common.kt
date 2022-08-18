package com.hyunjine.petplant.common

import android.content.res.Resources
import kotlin.math.roundToInt

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()

val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f)