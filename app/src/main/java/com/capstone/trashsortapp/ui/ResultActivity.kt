package com.capstone.trashsortapp.ui

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashsortapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    companion object {
        const val EXTRA_ID = "resultId"
        const val EXTRA_TITLE = "resultTitle"
        const val EXTRA_IMAGE = "image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

    }

    private fun getData(){
        val imageUri = intent.getParcelableExtra<Uri>("gambar")
        val klasifikasi = intent.getStringExtra(EXTRA_TITLE)

        binding.classes.text = klasifikasi
        binding.previewImageView.setImageURI(imageUri)
    }
}