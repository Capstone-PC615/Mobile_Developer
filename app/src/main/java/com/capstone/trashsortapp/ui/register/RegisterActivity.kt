package com.capstone.trashsortapp.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashsortapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}