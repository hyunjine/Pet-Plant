package com.hyunjine.petplant.common.custom_view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.dp
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import com.hyunjine.petplant.R.id as resId

class PlantProgressBar: ConstraintLayout {
    private lateinit var progressBar: ProgressBar

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )
    init {
        makeProgressBar(ProgressBar(context, null, androidx.appcompat.R.style.Widget_AppCompat_ProgressBar_Horizontal))
        makeSectionProgressBar()
    }

    fun setProgress(section: SectionList?) {
        val currentProgress = progressBar.progress
        Observable.interval(2L, TimeUnit.MILLISECONDS).map { it + 1 }
            .take(100L)
            .subscribe {
                progressBar.progress = currentProgress + it.toInt()
            }
    }

    private fun makeProgressBar(view: ProgressBar) {
        view.apply {
            progressDrawable = AppCompatResources.getDrawable(context, R.drawable.bg_plant_progressbar)
            max = 600
            progress = 100
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT).apply {
                topToTop = LayoutParams.PARENT_ID
                bottomToBottom = LayoutParams.PARENT_ID
                startToStart = LayoutParams.PARENT_ID
                endToEnd = LayoutParams.PARENT_ID
            }
            addView(this)
            progressBar = this
        }
    }

    private fun makeSectionProgressBar() {
        for (section in SectionList.values()) makeSectionProgressBar(section)
    }

    private fun makeSectionProgressBar(section: SectionList) {
        View(context).apply {
            id = section.id
            setBackgroundColor(Color.WHITE)
            layoutParams = LayoutParams(1.dp, 0).apply {
                val chain = getViewIdForChain(section)
                topToTop = LayoutParams.PARENT_ID
                bottomToBottom = LayoutParams.PARENT_ID
                when (section) {
                    SectionList.SECTION_1 -> {
                        startToStart = chain.first
                        endToStart = chain.second
                    }
                    SectionList.SECTION_5 -> {
                        startToEnd = chain.first
                        endToEnd = chain.second
                    }
                    else -> {
                        startToEnd = chain.first
                        endToStart = chain.second
                    }
                }
                matchConstraintPercentHeight = 0.75f
            }
            addView(this)
        }
    }

    private fun getViewIdForChain(viewId: SectionList): Pair<Int, Int> = when(viewId) {
        SectionList.SECTION_1 -> Pair(LayoutParams.PARENT_ID, SectionList.SECTION_2.id)
        SectionList.SECTION_2 -> Pair(SectionList.SECTION_1.id, SectionList.SECTION_3.id)
        SectionList.SECTION_3 -> Pair(SectionList.SECTION_2.id, SectionList.SECTION_4.id)
        SectionList.SECTION_4 -> Pair(SectionList.SECTION_3.id, SectionList.SECTION_5.id)
        SectionList.SECTION_5 -> Pair(SectionList.SECTION_4.id ,LayoutParams.PARENT_ID)
    }

    enum class SectionList(val id: Int) {
        SECTION_1(resId.section_1),
        SECTION_2(resId.section_2),
        SECTION_3(resId.section_3),
        SECTION_4(resId.section_4),
        SECTION_5(resId.section_5)
    }
}
