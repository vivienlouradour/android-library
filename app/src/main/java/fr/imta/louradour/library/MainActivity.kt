package fr.imta.louradour.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
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

class MainActivity : AppCompatActivity() {

    companion object {
        private val RANDOM = Random()
        const val baseUrl = "http://henri-potier.xebia.fr/"
    }

    private var bookRecyclerView: RecyclerView? = null
    private var bookAdapter: RecyclerView.Adapter<*>? = null
    var books: ArrayList<Book> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        getBooks()
    }

    private fun getBooks(){
        val retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl).build()

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
                    instanciateView()
                }
            }
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Timber.d("Erroreee")
            }
        })
    }

    private fun instanciateView(){
        bookRecyclerView = findViewById(R.id.books_recycler_view)
        bookAdapter = BookAdapter(this, books)
        bookRecyclerView!!.adapter = bookAdapter
        bookRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

}
