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
import pl.klonowski.library.adapters.ClientAdapter
import pl.klonowski.library.models.Client
import pl.klonowski.library.services.HttpService

class Clients : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clients)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val http = RetrofitClient.getInstance().create(HttpService::class.java)
        val switch = findViewById<Switch>(R.id.switchModDel)
        val btnAdd = findViewById<Button>(R.id.buttonAdd)

        CoroutineScope(Dispatchers.IO).launch {
            val clients = http.getClients().body()!!
            withContext(Dispatchers.Main) {
                try {
                    initRecyclerView(recyclerView, clients)
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
                // open activity modify client
            }
            return@setOnLongClickListener true
        }

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddClient::class.java)
            startActivity(intent)
        }
    }
    private fun initRecyclerView(recyclerView: RecyclerView, list: List<Client>) {
        recyclerView.adapter = ClientAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}