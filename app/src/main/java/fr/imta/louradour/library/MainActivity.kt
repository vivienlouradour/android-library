package fr.imta.louradour.library

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class MainActivity : AppCompatActivity(), BookAdapter.OnListClickListener {

    companion object {
        const val baseUrl = "http://henri-potier.xebia.fr/"
    }

    private var screenIsPortrait: Boolean? = null
    private val bookDetailsFragment = BookDetailsFragment()
    private val bookListFragment = BookListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        screenIsPortrait = resources.configuration.orientation == ORIENTATION_PORTRAIT

        setContentView(R.layout.activity_main)
        if(screenIsPortrait!!){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.portraitFrameLayout, bookListFragment, BookListFragment::class.java.name)
                .commit()
        }
        else{
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.leftContainer, bookListFragment, BookListFragment::class.java.name)
                .commit()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.rightContainer, bookDetailsFragment, BookDetailsFragment::class.java.name)
                .commit()
        }
    }

    override fun onClick(book: Book) {
        bookDetailsFragment.setBook(book)
        Timber.d("OnClick (screenIsPortrait=" + screenIsPortrait!!.toString() + ")")
        if(screenIsPortrait!!){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.portraitFrameLayout, bookDetailsFragment, BookDetailsFragment::class.java.name)
                .addToBackStack(BookDetailsFragment::class.java.name)
                .commit()
        }
        else{
            bookDetailsFragment.refreshData()
        }
    }
}
