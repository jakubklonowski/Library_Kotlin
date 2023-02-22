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
import pl.klonowski.library.adapters.LibraryAdapter
import pl.klonowski.library.models.LibraryJoin
import pl.klonowski.library.services.HttpService

class Libraries : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libraries)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var libraries: List<LibraryJoin> = emptyList()
        val switch = findViewById<Switch>(R.id.switchModDel)
        val btnAdd = findViewById<Button>(R.id.buttonAdd)
        val http = RetrofitClient.getInstance().create(HttpService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            libraries = http.getLibraries().body()!!
            withContext(Dispatchers.Main) {
                try {
                    initRecyclerView(recyclerView, libraries)
                }
                catch (e: Throwable) {
                    e.message?.let { Log.e("UWAGA", it) }
                }
            }
        }

        recyclerView.setOnLongClickListener {
            if (switch.isChecked) {
                // alert dialog delete
            }
            else {
                // open activity modify borrow
            }
            return@setOnLongClickListener true
        }

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddLibrary::class.java)
            startActivity(intent)
        }
    }
    private fun initRecyclerView(recyclerView: RecyclerView, list: List<LibraryJoin>) {
        recyclerView.adapter = LibraryAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}