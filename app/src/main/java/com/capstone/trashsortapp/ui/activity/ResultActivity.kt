package com.capstone.trashsortapp.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashsortapp.R
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

        val buttonAgain = findViewById<Button>(R.id.btn_again)

        buttonAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        getData()

    }

    private fun getData() {
        val imageUri = intent.getParcelableExtra<Uri>("gambar")
        val klasifikasi = intent.getStringExtra(EXTRA_TITLE)

        binding.classes.text = klasifikasi
        binding.previewImageView.setImageURI(imageUri)
    }
}