package com.hyunjine.petplant.view.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyunjine.petplant.common.custom_view.PlantProgressBar
import com.hyunjine.petplant.databinding.ActivityPostBinding

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
                }
            }
        }
    }

}