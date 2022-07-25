package com.hyunjine.petplant.view.post

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.databinding.ActivityPostBinding
import com.hyunjine.petplant.view.custom_view.PlantProgressBar
import com.hyunjine.petplant.view.splash.SplashActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        initView()
    }

    private fun setBinding() = ActivityPostBinding.inflate(layoutInflater).also {
        binding = it
        setContentView(it.root)
    }

    private fun initView() {
        binding.run {
            plantProgress.run {
                setOnClickListener {
                    setProgress(PlantProgressBar.SectionList.SECTION_1)
                    MainScope().launch {
                        delay(2000L)
                        Log.d(TAG, "initView: ")
                    }
                    startActivity(Intent(this@PostActivity, SplashActivity::class.java))
                    finish()
                }
            }
        }
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy:")
        super.onDestroy()
    }
}