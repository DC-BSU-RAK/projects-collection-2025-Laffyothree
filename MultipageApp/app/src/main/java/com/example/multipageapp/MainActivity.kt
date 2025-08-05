package com.example.multipageapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // lets your layout fit nicely under the system bars (like status bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // get the input fields and button
        val seasonInput = findViewById<EditText>(R.id.inputBarOne)
        val usernameInput = findViewById<EditText>(R.id.inputBarTwo)
        val goToSecondActivity = findViewById<Button>(R.id.goToSecondActivity)

        // what happens when the button is clicked
        goToSecondActivity.setOnClickListener {
            val season = seasonInput.text.toString().trim().lowercase()
            val username = usernameInput.text.toString().trim()

            // check if username is empty
            if (username.isEmpty()) {
                Toast.makeText(this, "please enter your username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // check the season input and go to the right screen
            when (season) {
                "summer" -> {
                    Toast.makeText(this, "welcome, $username!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity2Summer::class.java))
                }
                "winter" -> {
                    Toast.makeText(this, "welcome, $username!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity3Winter::class.java))
                }
                else -> {
                    Toast.makeText(this, "please input either 'summer' or 'winter'", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
