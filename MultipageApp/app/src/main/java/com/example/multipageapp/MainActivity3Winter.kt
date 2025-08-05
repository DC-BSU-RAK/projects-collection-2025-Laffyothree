package com.example.multipageapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton

class MainActivity3Winter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        // this helps adjust layout so it fits nicely around the system bars (like the status bar or navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // here we connect the buttons from the xml layout, same as summer activity file
        val imageButton = findViewById<ImageButton>(R.id.imageButton)     // first fragrance button (for women)
        val imageButton2 = findViewById<ImageButton>(R.id.imageButton2)   // second fragrance button (for women)
        val imageButton4 = findViewById<ImageButton>(R.id.imageButton4)   // first fragrance button (for men)
        val imageButton5 = findViewById<ImageButton>(R.id.imageButton5)   // second fragrance button (for men)

        // when first button is clicked, show details for libre intense
        imageButton.setOnClickListener {
            showModal(
                "Yves Saint Laurent Libre Intense",
                """
                Notes: Lavender, Orange Blossom, Vanilla, Tonka
                Vibe: Bold, warm, elegant femininity
                Price Range: $110 – $140
                """.trimIndent()
            )
        }

        // when second button is clicked, show details for baccarat rouge 540
        imageButton2.setOnClickListener {
            showModal(
                "Maison Francis Kurkdjian Baccarat Rouge 540",
                """
                Notes: Saffron, Amberwood, Fir Resin
                Vibe: Sweet, woody, ethereal — signature-worthy
                Price Range: $300 – $425 (niche/luxury)
                """.trimIndent()
            )
        }

        // when fourth button is clicked, show details for sauvage elixir
        imageButton4.setOnClickListener {
            showModal(
                "Dior Sauvage Elixir",
                """
                Notes: Cinnamon, Nutmeg, Lavender, Licorice, Vetiver
                Vibe: Spicy, bold, commanding presence
                Price Range: $135 – $170
                """.trimIndent()
            )
        }

        // when fifth button is clicked, show details for la nuit de l’homme
        imageButton5.setOnClickListener {
            showModal(
                "Yves Saint Laurent La Nuit de L’Homme",
                """
                Notes: Cardamom, Lavender, Cedarwood, Vetiver
                Vibe: Seductive, cozy, date-night ready
                Price Range: $95 – $120
                """.trimIndent()
            )
        }
    }

    // this function shows a popup dialog with the perfume details when a button is clicked
    private fun showModal(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}