package pl.klonowski.library

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.klonowski.library.models.Client
import pl.klonowski.library.models.ClientRequest
import pl.klonowski.library.services.HttpService

class AddClient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_client)

        val btnSave = findViewById<Button>(R.id.buttonSave)
        val btnClear = findViewById<Button>(R.id.buttonClear)
        val btnBack = findViewById<Button>(R.id.buttonBack)
        val clientName = findViewById<TextInputLayout>(R.id.textInputLayoutClientName)

        btnSave.setOnClickListener {
            val clientRequest = ClientRequest(
                clientName.editText?.text.toString()
            )
            val http = RetrofitClient.getInstance().create(HttpService::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                http.postClient(clientRequest)
            }
            clearForms(clientName)
        }

        btnClear.setOnClickListener {
            clearForms(clientName)
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, Client::class.java)
            startActivity(intent)
        }
    }

    private fun clearForms(vararg forms: TextInputLayout) {
        for (form in forms) {
            form.editText?.text = null
        }
    }
}