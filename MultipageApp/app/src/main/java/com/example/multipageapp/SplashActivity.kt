package com.example.multipageapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

// this suppresses the warning about not using the official SplashScreen API (which is okay for custom use cases)
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // sets the layout for this screen (likely a logo or animation)
        setContentView(R.layout.activity_splash)

        // wait for 4 seconds, then launch the MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // create and start an Intent to go to MainActivity
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))

            // finish this activity so the user can't come back to the splash screen by pressing back
            finish()
        }, 4000) // 4000 milliseconds = 4 seconds
    }
}