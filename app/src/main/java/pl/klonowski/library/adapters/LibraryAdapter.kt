package pl.klonowski.library.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.klonowski.library.R
import pl.klonowski.library.models.LibraryJoin

class LibraryAdapter(private val dataSet: List<LibraryJoin>)
    : RecyclerView.Adapter<LibraryAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewLBN: TextView
        val textViewLCN: TextView
        val textViewLibDate: TextView
        val textViewLibStatus: TextView

        init {
            textViewLBN = view.findViewById(R.id.textViewLCN)
            textViewLCN = view.findViewById(R.id.textViewLBN)
            textViewLibDate = view.findViewById(R.id.textViewLibDate)
            textViewLibStatus = view.findViewById(R.id.textViewLibStatus)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.library_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewLBN.text = dataSet[position].Book.Name
        holder.textViewLCN.text = dataSet[position].Client.Name
        holder.textViewLibDate.text = dataSet[position].Library.Date
        holder.textViewLibStatus.text = "Active: ${dataSet[position].Library.Active}"
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}