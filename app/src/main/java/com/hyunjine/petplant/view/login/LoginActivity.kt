package com.hyunjine.petplant.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyunjine.petplant.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        initView()
    }

    private fun setBinding() = ActivityLoginBinding.inflate(layoutInflater).also {
        binding = it
        setContentView(it.root)
    }

    private fun initView() {

    }
}