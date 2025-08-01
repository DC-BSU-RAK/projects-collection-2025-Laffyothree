package com.example.nancalculator

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // keeps track of which 2 buttons the user has selected
    private val selectedButtons = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // makes sure our layout doesn't get cut off by status bar or navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // when the user taps the help (information sign) button, show instructions
        val helpButton: ImageButton = findViewById(R.id.helpButton)
        helpButton.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.instructions, null)
            AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("OK", null)
                .create()
                .show()
        }

        // these are the four image buttons for AM, PM, Summer, and Winter
        val amButton = findViewById<ImageButton>(R.id.imageButton1)
        val pmButton = findViewById<ImageButton>(R.id.imageButton2)
        val summerButton = findViewById<ImageButton>(R.id.imageButton3)
        val winterButton = findViewById<ImageButton>(R.id.imageButton4)

        val imageButtons = listOf(amButton, pmButton, summerButton, winterButton)

        // handle button selection: tap to select or deselect, allow only 2 at a time
        imageButtons.forEach { button ->
            button.setOnClickListener {
                val id = button.id
                if (selectedButtons.contains(id)) {
                    // already selected - remove from list and reset appearance
                    selectedButtons.remove(id)
                    button.alpha = 1.0f
                } else {
                    // if less than 2 selected, add this one and make it look "pressed"
                    if (selectedButtons.size < 2) {
                        selectedButtons.add(id)
                        button.alpha = 0.5f
                    }
                }
            }
        }

        // now handle the search button
        val searchButton = findViewById<Button>(R.id.searchButton)

        // set the background color of the button to a dark gray (#545454)
        searchButton.setBackgroundColor(android.graphics.Color.parseColor("#545454"))

        searchButton.setOnClickListener {
            if (selectedButtons.size == 2) {
                // we have the two needed selections - show the result
                showResultDialog(selectedButtons.sorted())
            } else {
                // user hasn't picked two options yet - show a reminder
                Toast.makeText(this, "Please select 2 cards first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // pops up a dialog with the result image, based on the selected combination
    private fun showResultDialog(selected: List<Int>) {
        val comboKey = selected.joinToString("_")

        // match the selection combo to the correct result image
        val imageRes = when (comboKey) {
            "${R.id.imageButton1}_${R.id.imageButton3}" -> R.drawable.result_am_summer
            "${R.id.imageButton1}_${R.id.imageButton4}" -> R.drawable.result_am_winter
            "${R.id.imageButton2}_${R.id.imageButton3}" -> R.drawable.result_pm_summer
            "${R.id.imageButton2}_${R.id.imageButton4}" -> R.drawable.result_pm_winter
            else -> R.drawable.placeholder // fallback image
        }

        // inflate the result layout and set the image based on combo
        val dialogView = layoutInflater.inflate(R.layout.result_dialog, null)
        val resultImage = dialogView.findViewById<ImageView>(R.id.resultImage)
        resultImage.setImageResource(imageRes)

        // show the dialog with the result
        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Close", null)
            .create()
            .show()
    }
}
