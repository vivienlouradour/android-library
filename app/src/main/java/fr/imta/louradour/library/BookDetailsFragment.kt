package fr.imta.louradour.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BookDetailsFragment() : Fragment(){
    private var titleTxtView: TextView? = null
    private var book: Book? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_book_details, container, false)

        titleTxtView = view.findViewById(R.id.book_details_title)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshData()
    }

    private fun refreshData(){
        titleTxtView?.text = book?.title
    }

    fun setBook(book: Book){
        this.book = book
        refreshData()
    }
}