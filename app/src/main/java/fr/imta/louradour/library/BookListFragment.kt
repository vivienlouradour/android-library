package fr.imta.louradour.library

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

class BookListFragment : Fragment() {
    companion object {
        const val BOOKLIST_VAL = "BOOKLIST_STATE"
    }
    val books: ArrayList<Book> = ArrayList()

    private var bookRecyclerView: RecyclerView? = null
    private var bookAdapter: RecyclerView.Adapter<*>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getBooks()

        Timber.d("BookListFragment::onAttach")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_booklist, container, false)

        bookRecyclerView = view.findViewById(R.id.books_recycler_view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookAdapter = BookAdapter(this.context!!, books)
        bookRecyclerView!!.adapter = bookAdapter
        bookRecyclerView!!.layoutManager = LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)

        bookAdapter!!.notifyDataSetChanged()
    }


    private fun getBooks(){
        val retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MainActivity.baseUrl).build()

        val service = retrofit.create(HenriPotierService::class.java)
        val booksRequest = service.listBooks()

        booksRequest.enqueue(object: Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                val allBooks= response.body()
                allBooks?.let {
                    for( book in it) {
                        books.add(book)
                        Timber.d("Book ${book.title} ${book.price} ${book.isbn} ${book.cover}€")
                    }
                    bookAdapter?.notifyDataSetChanged()

                }
            }
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Timber.d("Error fetching books from API")
            }
        })
    }



}