package com.example.infosapps

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    fun tryToConnect(view: View) {

        val textFieldEmail = findViewById<EditText>(R.id.editTextText)
        val textFieldPassword = findViewById<EditText>(R.id.editTextText2)

        val email = textFieldEmail.text.toString()
        val Password = textFieldPassword.text.toString()
        val infos = "Email: $email\n PassWord : $Password"
        AlertDialog
            .Builder(this)
            .setTitle("Demande de connexion")
            .setMessage("Nouvelle Connexion \n Email : $email \n Password : $Password")
            .show()
    }
}