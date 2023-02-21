package pl.klonowski.library

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLibrary = findViewById<Button>(R.id.buttonBorrow)
        val btnBooks = findViewById<Button>(R.id.buttonBooks)
        val btnClients = findViewById<Button>(R.id.buttonClients)

        btnLibrary.setOnClickListener {
            val intent = Intent(this, Libraries::class.java)
            startActivity(intent)
        }

        btnBooks.setOnClickListener {
            val intent = Intent(this, Books::class.java)
            startActivity(intent)
        }

        btnClients.setOnClickListener {
            val intent = Intent(this, Clients::class.java)
            startActivity(intent)
        }

    }
}