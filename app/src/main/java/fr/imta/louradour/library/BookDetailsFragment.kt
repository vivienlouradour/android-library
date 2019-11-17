package fr.imta.louradour.library

import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import timber.log.Timber

class BookDetailsFragment() : Fragment(){
    private var book: Book? = null

    private var titleTxtView: TextView? = null
    private var coverImageView: ImageView? = null
    private var priceTxtView: TextView? = null
    private var synopsysTxtView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_book_details, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleTxtView = view.findViewById(R.id.book_details_title)
        coverImageView = view.findViewById(R.id.book_details_cover)
        priceTxtView = view.findViewById(R.id.book_details_price)
        synopsysTxtView = view.findViewById(R.id.book_details_synopsys)
        synopsysTxtView?.justificationMode = JUSTIFICATION_MODE_INTER_WORD

        refreshData()
    }

    fun refreshData(){
        if(book != null){
            val syn = book?.getFormattedSynopsys()
            Timber.d(syn)
            Picasso.get().load(book!!.cover).into(coverImageView)
            titleTxtView?.text = book?.title
            priceTxtView?.text = book?.getFormattedPrice()
            synopsysTxtView?.text = book?.getFormattedSynopsys()
        }
    }

    fun setBook(book: Book){
        this.book = book
    }
}