package com.example.eventapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        extractBookingDataFromIntent()
    }

    private fun extractBookingDataFromIntent() {
        val duree = intent.getIntExtra("EXTRA_DUREE", 0) // Assurez-vous que le type est Int
        val date = intent.getStringExtra("EXTRA_DATE") ?: "Date non définie"

        displayBookingData(duree, date)
    }

    private fun displayBookingData(duree: Int, date: String) {
        val dureeTextView = findViewById<TextView>(R.id.dureeTextView)
        val dateTextView = findViewById<TextView>(R.id.dateTextView)

        dureeTextView.text = "Durée du séjour : $duree nuits"
        dateTextView.text = "Date d'arrivée : $date"
    }

}