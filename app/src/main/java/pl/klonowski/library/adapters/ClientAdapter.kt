package pl.klonowski.library.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.klonowski.library.R
import pl.klonowski.library.models.Client

class ClientAdapter(private val dataSet: List<Client>)
    : RecyclerView.Adapter<ClientAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewClientName: TextView

        init {
            textViewClientName = view.findViewById(R.id.textViewClientName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.client_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewClientName.text = dataSet[position].Name
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
