package pl.klonowski.library.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.klonowski.library.R
import pl.klonowski.library.models.Book

class BookAdapter(private val dataSet: List<Book>)
    : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewBookName: TextView
        val textViewBookAuthor: TextView

        init {
            textViewBookName = view.findViewById(R.id.textViewBookName)
            textViewBookAuthor = view.findViewById(R.id.textViewBookAuthor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewBookName.text = dataSet[position].Name
        holder.textViewBookAuthor.text = dataSet[position].Author
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}