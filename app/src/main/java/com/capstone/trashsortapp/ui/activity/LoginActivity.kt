package com.capstone.trashsortapp.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.capstone.trashsortapp.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnLogin = binding.btnLogin
        val tvRegister = binding.tvRegister
        val btnLoginWithGoogle = binding.btnLoginWithGoogle
        val clientId = "334953559362-4s08bcsv399j8u6h1rv6b2nho7j8rrf7.apps.googleusercontent.com"

        val textColor =
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                ContextCompat.getColor(this, android.R.color.white)
            } else {
                ContextCompat.getColor(this, android.R.color.black)
            }

        binding.tvDesc.setTextColor(textColor)
        binding.tvQuestion.setTextColor(textColor)
        binding.tvWelcome.setTextColor(textColor)

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        FirebaseApp.initializeApp(this)

        firebaseAuth = Firebase.auth


        btnLoginWithGoogle.setOnClickListener {
            signIn()
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val edEmail = binding.edLoginEmail.text.toString()
            val edPassword = binding.edLoginPassword.text.toString()
            if (edEmail.isNotEmpty() && edPassword.isNotEmpty()) {

                binding.progressBar.visibility = View.VISIBLE
                firebaseAuth.signInWithEmailAndPassword(edEmail, edPassword).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.progressBar.visibility = View.GONE
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        finish()
                    } else {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
        binding.progressBar.visibility = View.VISIBLE
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    binding.progressBar.visibility = View.GONE
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user: FirebaseUser? = firebaseAuth.currentUser
                    updateUI(user)
                } else {
                    binding.progressBar.visibility = View.GONE
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        updateUI(currentUser)
    }

    companion object {
        private const val TAG = "LoginActivity"
    }

}