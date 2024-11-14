package com.example.androidtp2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialisation des champs et du bouton
        emailField = findViewById(R.id.txtMail)
        passwordField = findViewById(R.id.txtPassword)
        loginButton = findViewById(R.id.btnConect)

        // Connexion de la méthode login au bouton de connexion
        loginButton.setOnClickListener {
            login()
        }
    }

    // Méthode pour lancer l'inscription
    fun registerNewAccount(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    // Méthode pour gérer la connexion
    private fun login() {
        val email = emailField.text.toString()
        val password = passwordField.text.toString()

        if (email.isNotBlank() && password.isNotBlank()) {
            val loginData = LoginData(email, password)

            Api().post(
                "https://mypizza.lesmoulinsdudev.com/auth",
                loginData,
                onSuccess = { responseCode, token: String? ->
                    if (responseCode == 200 && token != null) {
                        loginSuccess(responseCode, token)
                    } else {
                        Toast.makeText(
                            this,
                            "Erreur de connexion. Vérifiez vos informations.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                securityToken = null
            )
        }else{
            Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show()
        }
    }

    // Méthode pour gérer le succès de la connexion
    private fun loginSuccess(responseCode: Int, token: String?) {
        if (responseCode == 200 && token != null) {
            val intent = Intent(this, OrdersActivity::class.java)
            intent.putExtra("TOKEN", token)
            startActivity(intent)
        }
    }
}
