package com.capstone.trashsortapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashsortapp.databinding.ActivityRegisterBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val nameUser = binding.edRegisterName.text.toString()
            val emailUser = binding.edRegisterEmail.text.toString()
            val passwordUser = binding.edRegisterPassword.text.toString()
            val confPassword = binding.edConfPass.text.toString()

            if (nameUser.isNotEmpty() && emailUser.isNotEmpty() && passwordUser.isNotEmpty() && confPassword.isNotEmpty()) {
                if (passwordUser == confPassword) {
                    binding.progressBar.visibility = View.VISIBLE
                    firebaseAuth.createUserWithEmailAndPassword(emailUser, passwordUser)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Successful Registration", Toast.LENGTH_SHORT).show()
                                onBackPressed()
                            } else {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}