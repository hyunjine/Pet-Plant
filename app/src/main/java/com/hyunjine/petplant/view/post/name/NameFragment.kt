package com.hyunjine.petplant.view.post.name

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hyunjine.petplant.R
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentNameBinding
import com.hyunjine.petplant.view.post.name.vm.NameViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hyunjine.petplant.common.TAG

class NameFragment: BaseFragment<FragmentNameBinding, NameViewModel>() {
    override fun setViewModel() {

    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentNameBinding =
        FragmentNameBinding.inflate(lf, ct, false)

    override fun initView() {
        binding.run {
            etEmail.addTextChangedListener {
                Log.d(TAG, "initView: $it")
                checkEmailFormatEditText(it.toString())
            }
        }
    }
    private fun checkEmailFormatEditText(userEmail: String) {
        binding.layName.run {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                setFailText(R.string.txt_error_name)
                boxStrokeColor = getColor(requireContext(), R.color.red)
                hintTextColor = getColorStateList(requireContext(), R.color.red)
            } else {
                boxStrokeColor = getColor(requireContext(), R.color.main_green)
                hintTextColor = getColorStateList(requireContext(), R.color.main_green)
                setGoneFailText()
            }
        }
    }
    private fun setFailText(strId: Int) {
        binding.layName.hint = getString(strId)
    }

    private fun setGoneFailText() {
        binding.layName.hint = ""
    }
}