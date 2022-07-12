package com.hyunjine.petplant.view.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.hyunjine.petplant.R
import com.hyunjine.petplant.databinding.LayoutLoadingBinding

class LoadingProgressBar: ConstraintLayout {
    private lateinit var binding: LayoutLoadingBinding

    constructor(context: Context) : super(context){
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(context)
    }

    private fun init(context :Context){
        binding = LayoutLoadingBinding.bind(LayoutInflater.from(context).inflate(R.layout.layout_loading,this,false))
        addView(binding.root)
    }

    fun showProgressBar() { binding.layLoading.visibility = View.VISIBLE }

    fun hideProgressBar() { binding.layLoading.visibility = View.GONE }
}