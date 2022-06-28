package com.hyunjine.petplant.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.databinding.ActivityMainBinding
import java.sql.Time
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        initView()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }
    private fun setBinding() = ActivityMainBinding.inflate(layoutInflater).also {
        binding = it
        setContentView(it.root)
        getTime()
    }

    private fun initView() {
        binding.run {
            bottomAppBar.fabCradleRoundedCornerRadius = 0f
            bottomNavigationView.apply {
                background = null
                menu.getItem(1).isEnabled = false
            }
        }
    }

    private fun getTime() {
        val sleepTime = Pair(0, 30)
        val wakeTime = Pair(8, 29)

        val sleepCalendar = getCalendar(sleepTime.first, sleepTime.second)
        val wakeCalendar = getCalendar(wakeTime.first, wakeTime.second)
        if (wakeCalendar.before(sleepCalendar)) wakeCalendar.add(Calendar.DATE, 1)

        var interval = wakeCalendar.timeInMillis - sleepCalendar.timeInMillis
        interval -= TimeUnit.DAYS.toMillis(TimeUnit.MILLISECONDS.toDays(interval))
        val hours = TimeUnit.MILLISECONDS.toHours(interval).toInt()
        interval -= TimeUnit.HOURS.toMillis(hours.toLong())
        val minutes = TimeUnit.MILLISECONDS.toMinutes(interval).toInt()

        Log.d(TAG, "총 시간: ${hours}시간 ${minutes}분")

        when (checkSleepTime(hours, minutes)) {
            RecommendTimeResult.LESS -> Log.d(TAG, "수면이 부족해요")
            RecommendTimeResult.MORE -> Log.d(TAG, "수면이 많아요")
            RecommendTimeResult.RIGHT -> Log.d(TAG, "적당해요")
        }
    }

    private fun checkSleepTime(hour: Int, minute: Int): RecommendTimeResult {
        val sleepTime = getCalendar(hour, minute)
        val (min, max) = getRecommendTimeByAge(25)
        return when {
            sleepTime.before(min) -> RecommendTimeResult.LESS
            sleepTime.after(max) -> RecommendTimeResult.MORE
            else -> RecommendTimeResult.RIGHT
        }
    }

    private fun getRecommendTimeByAge(age: Int): Pair<Calendar, Calendar> = when(age) {
        in 6..13 -> Pair(getCalendar(9), getCalendar(11))
        in 14..18 -> Pair(getCalendar(8), getCalendar(10))
        in 19..64 -> Pair(getCalendar(7), getCalendar(9))
        else -> Pair(getCalendar(7), getCalendar(8))
    }


    private fun getCalendar(hour: Int, minute: Int): Calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }

    private fun getCalendar(hour: Int): Calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, 0)
    }

    enum class RecommendTimeResult {
        LESS,
        RIGHT,
        MORE
    }
}