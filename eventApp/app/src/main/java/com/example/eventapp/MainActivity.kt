package com.example.eventapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private  var bookingDate : String = ""
    private var stayDuration : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        initCalendarEvent()
        initSeekBArEvent()

        val button = findViewById<Button>(R.id.button)


        button.setOnClickListener(){
            val intent = Intent(this, MainActivity3::class.java)

            intent.putExtra("EXTRA_DUREE", stayDuration) // Envoyer stayDuration comme un Int
            intent.putExtra("EXTRA_DATE", bookingDate)
            startActivity(intent)

        }
    }

    private fun initCalendarEvent() {
        val calendar = findViewById<CalendarView>(R.id.calendarView)

        calendar.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
                // Traitez la date sélectionnée ici, par exemple, en la stockant sous forme de chaîne.
                bookingDate = "$dayOfMonth/${month + 1}/$year" // Le mois commence à 0, donc on ajoute 1.

                updateBookingDateLabel() // pour mettre à jour à chaque selection d'une date

            }
        })
    }

    private fun updateBookingDateLabel() {
        val bookingDateLabel = findViewById<TextView>(R.id.textView2)
        bookingDateLabel.text = "Date d'arrivée : $bookingDate"
    }

    private fun initSeekBArEvent(){
        val seekbar = findViewById<SeekBar>(R.id.seekBar)
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                stayDuration = p1
                updateStayDurationLabel()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }

    private fun updateStayDurationLabel() {
        val stayDurationLabel = findViewById<TextView>(R.id.textView3)
        stayDurationLabel.text = "Durée du séjour : $stayDuration nuits"
    }

    private fun onClick() {

    }
}