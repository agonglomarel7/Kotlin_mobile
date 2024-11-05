import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eventapp.R


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        extractBookingDataFromIntent()
    }

    private fun extractBookingDataFromIntent() {
        // Extraire les données de l'Intent
        val duree = intent.getStringExtra("EXTRA_DUREE") ?: "Nom inconnu"
        val date = intent.getStringExtra("EXTRA_DATE") ?: "Date non définie"

        displayBookingData(duree,date)
    }

    private fun displayBookingData(duree: String, date: String) {
        // Récupérer les TextView depuis le layout
        val dureeTextView = findViewById<TextView>(R.id.dureeTextView)
        val dateTextView = findViewById<TextView>(R.id.dateTextView)

        // Mettre à jour les TextView avec les informations de réservation
        dureeTextView.text = "Nom : $duree"
        dateTextView.text = "Date : $date"

    }
}