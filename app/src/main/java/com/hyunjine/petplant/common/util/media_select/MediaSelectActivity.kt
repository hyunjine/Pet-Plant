package com.hyunjine.petplant.common.util.media_select

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyunjine.petplant.common.TAG
import com.hyunjine.petplant.common.base.BaseActivity
import com.hyunjine.petplant.common.util.media_select.adapter.MediaSelectAdapter
import com.hyunjine.petplant.databinding.ActivityMediaSelectBinding

class MediaSelectActivity : BaseActivity<ActivityMediaSelectBinding>(ActivityMediaSelectBinding::inflate) {
    private lateinit var activityResult: ActivityResultLauncher<Intent>
    private val imageUriList: MutableList<Uri> by lazy { mutableListOf() }
    private val adapter: MediaSelectAdapter by lazy { MediaSelectAdapter() }

    override fun initVew() {
        activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode != RESULT_OK) {
                Log.e(TAG, "activityResult fail ")
                return@registerForActivityResult
            }

            it.data?.let { data ->
                if (data.clipData == null) {
                    Log.d(TAG, "이미지 하나 선택: ${data.data}")
                    data.data?.let { uri ->
                        imageUriList.add(uri)
                        adapter.setMediaList(imageUriList)
                        binding.recyclerView.apply {
                            this.adapter = this@MediaSelectActivity.adapter
                            layoutManager = LinearLayoutManager(
                                this@MediaSelectActivity,
                                LinearLayoutManager.HORIZONTAL,
                                true
                            )
                            setHasFixedSize(true)
                        }
                    }
                } else {
                    val clipData = data.clipData!!
                    Log.d(TAG, "이미지 여러개 선택: ${clipData.itemCount}")

                    if (clipData.itemCount > 10) {
                        Toast.makeText(this, "사진은 10장까지 선택 가능합니다", Toast.LENGTH_SHORT).show()
                    } else {
                        for (i: Int in 0 until clipData.itemCount) {
                            val imageUri = clipData.getItemAt(i).uri
                            runCatching {
                                imageUriList.add(imageUri)
                                adapter.setMediaList(imageUriList)
                            }.onSuccess {
                                binding.recyclerView.apply {
                                    this.adapter = this@MediaSelectActivity.adapter
                                    layoutManager = LinearLayoutManager(
                                        this@MediaSelectActivity,
                                        LinearLayoutManager.HORIZONTAL,
                                        true
                                    )
                                    setHasFixedSize(true)
                                }
                            }.onFailure {

                            }
                        }
                    }
                }
            }
        }
        onClickEvent()
    }

    private fun onClickEvent() = binding.run {
        getImage.setOnClickListener {
            Intent(Intent.ACTION_PICK).run {
                type = MediaStore.Images.Media.CONTENT_TYPE
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                activityResult.launch(this)
            }
        }
    }
}