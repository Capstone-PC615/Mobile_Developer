package com.capstone.trashsortapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashsortapp.databinding.ActivityLoginBinding
import com.capstone.trashsortapp.ui.MainActivity
import com.capstone.trashsortapp.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val etEmail = binding.tiLayoutEditTextEmail
        val etPassword = binding.tiLayoutEditTextPassword
        val btnLogin = binding.btnLogin
        val tvRegister = binding.tvRegister
        val btnRegisterWithGoogle = binding.btnLoginWithGoogle

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            // Kirim data ke API menggunakan ViewModel
            // val email = et_email.text.toString()
            // val password = et_password.text.toString()
            // viewModel.login(email, password)
            // Implementasikan logika untuk penanganan respons dari API
            // dan perpindahan ke halaman utama (MainActivity) setelah login berhasil
            goToMain()
        }

    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Opsional, jika Anda ingin menghapus aktivitas login dari tumpukan aktivitas setelah login berhasil
    }

}