package fr.imta.louradour.library

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class BookAdapter(private val context: Context, private val books: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var listener: OnListClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        listener = context as OnListClickListener

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.titleItemView.text = books[position].title
        holder.priceItemView.text = books[position].getFormattedPrice()

        holder.bookItemView.setOnClickListener{
            listener!!.onClick(books[position])
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }

    inner class BookViewHolder(val bookItemView: View) : RecyclerView.ViewHolder(bookItemView) {

        internal var titleItemView: TextView = bookItemView.findViewById<View>(R.id.book_title) as TextView
        internal var priceItemView: TextView = bookItemView.findViewById<View>(R.id.book_price) as TextView

    }

    interface OnListClickListener{
        fun onClick(book: Book)
    }
}