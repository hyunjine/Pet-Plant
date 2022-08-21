package com.hyunjine.petplant.view.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyunjine.petplant.R
import com.hyunjine.petplant.databinding.ActivityPostBinding
import com.hyunjine.petplant.view.a_module.custom_view.PlantProgressBar
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
                    }
                    finish()
                    overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }
}