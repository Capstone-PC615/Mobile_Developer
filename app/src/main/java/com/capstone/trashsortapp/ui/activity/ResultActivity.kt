package com.capstone.trashsortapp.ui.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashsortapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    companion object {
        const val EXTRA_TITLE = "resultTitle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        binding.btnAgain.setOnClickListener {
            onBackPressed()
        }

    }

    private fun getData(){
        val imageUri = intent.getParcelableExtra<Uri>("picture")
        val filter = intent.getStringExtra(EXTRA_TITLE)

        binding.classes.text = filter
        binding.previewImageView.setImageURI(imageUri)
    }
}