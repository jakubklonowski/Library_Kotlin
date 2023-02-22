package pl.klonowski.library

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.klonowski.library.models.BookRequest
import pl.klonowski.library.services.HttpService

class AddBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        val btnSave = findViewById<Button>(R.id.buttonSave)
        val btnClear = findViewById<Button>(R.id.buttonClear)
        val btnBack = findViewById<Button>(R.id.buttonBack)
        val bookName = findViewById<TextInputLayout>(R.id.textInputLayoutBookName)
        val bookAuthor = findViewById<TextInputLayout>(R.id.textInputLayoutBookAuthor)

        btnSave.setOnClickListener {
            val bookRequest = BookRequest(bookName.editText?.text.toString(), bookAuthor.editText?.text.toString())
            val http = RetrofitClient.getInstance().create(HttpService::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                http.postBook(bookRequest)
            }
            clearForms(bookName, bookAuthor)
        }

        btnClear.setOnClickListener {
            clearForms(bookName, bookAuthor)
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, Books::class.java)
            startActivity(intent)
        }
    }
    private fun clearForms(vararg forms: TextInputLayout) {
        for (form in forms) {
            form.editText?.text = null
        }
    }
}