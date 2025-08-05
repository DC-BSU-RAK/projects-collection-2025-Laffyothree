package com.example.multipageapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton

class MainActivity2Summer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        // // this helps adjust layout so it fits nicely around the system bars (like the status bar or navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // connecting the image buttons from the layout
        val imageButton = findViewById<ImageButton>(R.id.imageButton)     // first fragrance button (for women)
        val imageButton2 = findViewById<ImageButton>(R.id.imageButton2)   // second fragrance button (for women)
        val imageButton4 = findViewById<ImageButton>(R.id.imageButton4)   // first fragrance button (for men)
        val imageButton5 = findViewById<ImageButton>(R.id.imageButton5)   // second fragrance button (for men)

        // when first button is clicked, show details for aqua universalis
        imageButton.setOnClickListener {
            showModal(
                "Maison Francis Kurkdjian Aqua Universalis",
                """
                Notes: Bergamot, Lemon, White Flowers, Musk
                Vibe: Clean, airy, luxury soap-like freshness
                Price Range: $215 – $270 (niche/luxury)
                """.trimIndent()
            )
        }

        // when second button is clicked, show details for eau de soleil blanc
        imageButton2.setOnClickListener {
            showModal(
                "Tom Ford Eau de Soleil Blanc",
                """
                Notes: Neroli, Coconut, Ylang-Ylang, Amber
                Vibe: Tropical, creamy, sophisticated
                Price Range: $125 – $155
                """.trimIndent()
            )
        }

        // when fourth button is clicked, show details for virgin island water
        imageButton4.setOnClickListener {
            showModal(
                "Creed Virgin Island Water",
                """
                Notes: Lime, Coconut, Rum, Sugarcane
                Vibe: Tropical cocktail, high-end beach trip
                Price Range: $310 – $400 (niche/luxury)
                """.trimIndent()
            )
        }

        // when fifth button is clicked, show details for allure homme sport cologne
        imageButton5.setOnClickListener {
            showModal(
                "Chanel Allure Homme Sport Cologne",
                """
                Notes: Mandarin, Neroli, Cedar, White Musk
                Vibe: Crisp, energetic, everyday luxury
                Price Range: $95 – $120
                """.trimIndent()
            )
        }
    }

    // this function shows a popup with perfume details when a button is tapped
    private fun showModal(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}