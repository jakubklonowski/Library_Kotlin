package pl.klonowski.library

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.klonowski.library.models.*
import pl.klonowski.library.services.HttpService
import java.time.LocalDateTime

class AddLibrary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_library)

        val spinnerBook = findViewById<Spinner>(R.id.spinnerBook)
        val spinnerClient = findViewById<Spinner>(R.id.spinnerClient)
        val http = RetrofitClient.getInstance().create(HttpService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val books = http.getBooks().body()!!
            val clients = http.getClients().body()!!
            withContext(Dispatchers.Main) {
                try {
                    initSpinner(spinnerBook, books)
                    initSpinner(spinnerClient, clients)
                }
                catch (e: Throwable) {
                    e.message?.let { Log.e("UWAGA", it) }
                }
            }
        }

        val btnAdd = findViewById<Button>(R.id.buttonSave)
        val btnBack = findViewById<Button>(R.id.buttonBack)

        btnAdd.setOnClickListener {
            val book = spinnerBook.selectedItem as Book
            val client = spinnerClient.selectedItem as Client
            val library = LibraryRequest(LocalDateTime.now().toString(), true)

            val libraryReq = LibraryRequestJoin(library, book, client)
            CoroutineScope(Dispatchers.IO).launch {
                http.postLibrary(libraryReq)
            }

            Toast.makeText(
                this@AddLibrary,
                "${client.Name} borrowed book ${book.Name}",
                Toast.LENGTH_LONG)
                .show()
            goBack()
        }

        btnBack.setOnClickListener {
            goBack()
        }
    }
    private fun initSpinner(spinner: Spinner, list: List<Any>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        spinner.adapter = adapter
    }
    private fun goBack() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}