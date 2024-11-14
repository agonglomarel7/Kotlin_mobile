package com.example.androidtp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val boutonRegister = findViewById<Button>(R.id.btnRegister)
        boutonRegister.setOnClickListener {
            register()
        }

    }

    public fun goToLogin(view: View)
    {
        finish();
    }

    public  fun register(){

        val lblName = findViewById<EditText>(R.id.txtRegisterName)
        val lblEmail = findViewById<EditText>(R.id.txtRegisterMail)
        val lblPassword = findViewById<EditText>(R.id.txtRegisterPassword)


        val name = lblName.text.toString()
        val email = lblEmail.text.toString()
        val password = lblPassword.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            Log.d("RegisterActivity", "Les champs ne sont pas vides. Tentative d'inscription...")  // Log pour vérifier que les champs sont remplis

            val registerData = RegisterData(name, email, password)


            Api().post<RegisterData>(
                "https://mypizza.lesmoulinsdudev.com/register",
                registerData,
                ::registerSuccess
                )
        }else{
            Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show()
        }
    }

    public fun registerSuccess(responseCode:Int) {
        if (responseCode == 200){
            /*
            val intentTologinActivity = Intent(
                    this, //Contexte de départ
                    LoginActivity::class.java //Activité cible indiquée explicitement
            );
            Toast.makeText(this, "Inscription réussie!", Toast.LENGTH_SHORT).show()
            startActivity(intentTologinActivity);

             */
            finish()


        }else {
            Toast.makeText(this, "Inscription échouée", Toast.LENGTH_SHORT).show()
        }
    }

}