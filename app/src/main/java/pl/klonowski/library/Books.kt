package pl.klonowski.library

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.klonowski.library.adapters.BookAdapter
import pl.klonowski.library.models.Book
import pl.klonowski.library.services.HttpService

class Books : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val http = RetrofitClient.getInstance().create(HttpService::class.java)
        val switch = findViewById<Switch>(R.id.switchModDel)
        val btnAdd = findViewById<Button>(R.id.buttonAdd)

        CoroutineScope(Dispatchers.IO).launch {
            val books = http.getBooks().body()!!
            withContext(Dispatchers.Main) {
                try {
                    initRecyclerView(recyclerView, books)
                }
                catch (e: Throwable) {
                    e.message?.let { Log.e("UWAGA", it) }
                }
            }
        }

        recyclerView.setOnLongClickListener {
            if (switch.isChecked) {
                val dialogDel = DeleteDialog()
                dialogDel.show(supportFragmentManager, "deleteBook")
            }
            else {
                // open activity modify book
            }
            return@setOnLongClickListener true
        }

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddBook::class.java)
            startActivity(intent)
        }
    }
    private fun initRecyclerView(recyclerView: RecyclerView, list: List<Book>) {
        recyclerView.adapter = BookAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isLongClickable = true
    }
}