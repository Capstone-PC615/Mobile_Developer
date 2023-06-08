package com.capstone.trashsortapp.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashsortapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}