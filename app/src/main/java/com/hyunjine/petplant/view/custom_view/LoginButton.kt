package com.hyunjine.petplant.view.custom_view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.hyunjine.petplant.R
import com.hyunjine.petplant.databinding.LayoutLoginBinding

class LoginButton: ConstraintLayout {
    private lateinit var binding: LayoutLoginBinding
    private lateinit var listener: () -> Unit
    fun setOnClickLoginListener(listener: () -> Unit) {
        this.listener = listener
    }
    constructor(context: Context) : super(context){
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init(context)
        getAttrs(attrs)
    }

    private fun init(context : Context){
        binding = LayoutLoginBinding.bind(LayoutInflater.from(context).inflate(R.layout.layout_login,this,false))
        addView(binding.root)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        binding.layoutLogin.setOnClickListener {
            if (::listener.isInitialized) listener()
        }
    }

    @SuppressLint("Recycle")
    private fun getAttrs(attrs: AttributeSet) =
        context.obtainStyledAttributes(attrs, R.styleable.LoginButton).run {
            binding.run {
                val bgResId = getResourceId(R.styleable.LoginButton_bg, R.drawable.bg_round_edge_rectangle_kakao)
                layoutLogin.setBackgroundResource(bgResId)
                val imgResId = getResourceId(R.styleable.LoginButton_ic, R.drawable.ic_kakao)
                imgIcon.setImageResource(imgResId)
                val textColorId = getResourceId(R.styleable.LoginButton_textColor, R.color.black)
                tvLogin.setTextColor(context.getColor(textColorId))
                val textId = getResourceId(R.styleable.LoginButton_text, R.string.kakao_login)
                tvLogin.setText(textId)
            }
            recycle()
        }
}