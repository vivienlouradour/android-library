package fr.imta.louradour.library

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(context: Context, private val books: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = inflater.inflate(R.layout.list_item_layout, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.titleItemView.text = books[position].title
        holder.priceItemView.text = books[position].getFormattedPrice()
    }

    override fun getItemCount(): Int {
        return books.size
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var titleItemView: TextView = itemView.findViewById<View>(R.id.book_title) as TextView
        internal var priceItemView: TextView = itemView.findViewById<View>(R.id.book_price) as TextView

    }
}