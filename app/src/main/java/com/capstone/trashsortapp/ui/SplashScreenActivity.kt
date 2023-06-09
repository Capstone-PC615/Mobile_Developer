package com.capstone.trashsortapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.capstone.trashsortapp.R
import com.capstone.trashsortapp.ui.activity.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private val splashScreenDuration = 3000L // Durasi splash screen dalam milidetik (misalnya 3000 ms = 3 detik)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, splashScreenDuration)
    }

}