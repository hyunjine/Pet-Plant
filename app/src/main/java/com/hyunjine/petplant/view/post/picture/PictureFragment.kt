package com.hyunjine.petplant.view.post.picture

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.common.base.BaseFragment
import com.hyunjine.petplant.databinding.FragmentPictureBinding
import com.hyunjine.petplant.view.main.MainActivity
import com.hyunjine.petplant.view.post.picture.vm.PictureViewModel
import com.hyunjine.petplant.view.view_pager.ViewPagerFragment

class PictureFragment: BaseFragment<FragmentPictureBinding, PictureViewModel>() {
    private lateinit var activityResult: ActivityResultLauncher<Intent>
    private val viewPagerFragment: ViewPagerFragment by lazy { ViewPagerFragment() }
    private val imageUriList: MutableList<Uri> by lazy { mutableListOf() }

    override fun initView() = binding.run {
        setViewPagerFragment(viewPagerFragment)
        onResultMediaActivity()
        onClickEvent()
    }

    private fun onResultMediaActivity() {
        activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode != AppCompatActivity.RESULT_OK) {
                Log.e(TAG, "activityResult fail ")
                return@registerForActivityResult
            }

            it.data?.let { data ->
                if (data.clipData == null) {
                    Log.d(TAG, "이미지 하나 선택: ${data.data}")
                    data.data?.let { uri ->
                        imageUriList.add(uri)
                        viewPagerFragment.setImageResource(imageUriList)
                    }
                } else {
                    val clipData = data.clipData!!
                    Log.d(TAG, "이미지 여러개 선택: ${clipData.itemCount}")

                    if (clipData.itemCount > 10) {
                        Toast.makeText(requireContext(), "사진은 10장까지 선택 가능합니다", Toast.LENGTH_SHORT).show()
                    } else {
                        for (i: Int in 0 until clipData.itemCount) {
                            val imageUri = clipData.getItemAt(i).uri
                            runCatching {
                                imageUriList.add(imageUri)
                            }.onSuccess {
                                viewPagerFragment.setImageResource(imageUriList)
                            }.onFailure {
                                Log.e(TAG, "onResultMediaActivity: ", it)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setViewPagerFragment(fragment: ViewPagerFragment) =
        requireActivity().supportFragmentManager.beginTransaction().run {
            replace(binding.fragViewPager.id, fragment)
            commit()
            requireContext().fi
        }

    private fun onClickEvent() = binding.run {
        imgCamera.setOnClickListener {
        }
        clPostPicture.setOnClickListener {
            startMediaActivity()
        }
    }

    private fun startMediaActivity() = Intent(Intent.ACTION_PICK).run {
        type = MediaStore.Images.Media.CONTENT_TYPE
        putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        activityResult.launch(this)
    }

    override fun setViewModel() {
        viewModel = ViewModelProvider(this)[PictureViewModel::class.java]
    }

    override fun setBinding(lf: LayoutInflater, ct: ViewGroup?): FragmentPictureBinding =
        FragmentPictureBinding.inflate(lf, ct, false)
}
